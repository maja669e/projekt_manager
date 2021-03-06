package demo.controllers;

import demo.data.DataFacadeImpl;
import demo.model.*;
import demo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;


@Controller
public class TaskController {

    private TaskService taskService = new TaskService(new DataFacadeImpl());

    /**
     * @author Nicolai Okkels
     * makes a hashmap to check if the key (taskUser) is in the list, so that the same user cannot be added twice
     */
    @PostMapping("addTaskUser")
    public String addTaskUser(WebRequest request) throws ProjectManagerException {
        //Retrieve values from HTML form via WebRequest
        String userName = request.getParameter("username");
        int taskid = Integer.parseInt(request.getParameter("taskid"));

        if (!userName.equals("placeholder")) {
            Task task = taskService.getTask(taskid);
            task.setTaskMembers(taskService.getTaskMembers(taskid));

            User taskUser = taskService.getTaskUser(userName);
            HashMap<String, User> hashMap = new HashMap<>();

            for (int i = 0; i < task.getTaskMembers().size(); i++) {
                hashMap.put(task.getTaskMembers().get(i).getUserName(), task.getTaskMembers().get(i));
            }

            //Cant have two of the same users on a task
            if (!hashMap.containsKey(userName)) {
                taskService.addMemberToTask(taskid, taskUser.getUserid());
            }
        }

        return "redirect:/projekt";

    }

    /**
     * @author Nicolai Okkels
     * gets userid and taskid and delete task
     */
    @PostMapping("deleteTaskUser")
    public String deleteTaskUser(WebRequest request) throws ProjectManagerException {
        //Retrieve values from HTML form via WebRequest
        int userid = Integer.parseInt(request.getParameter("userid"));
        int taskid = Integer.parseInt(request.getParameter("taskid"));

        if (userid != 0) {
            taskService.deleteMemberFromTask(taskid, userid);
        }

        return "redirect:/projekt";
    }

    /**
     * @author Maja Bijedic
     *  Get subprojectid to add task with taskname to the correct project in session
     */
    @PostMapping("addTask")
    public String addTask(WebRequest request) throws ProjectManagerException {
        // Retrieve object from web request (session scope)
        Project project = (Project) request.getAttribute("project", WebRequest.SCOPE_SESSION);

        //Retrieve values from HTML form via WebRequest
        String taskName = request.getParameter("taskname");
        int subprojectid = Integer.parseInt(request.getParameter("subprojectid"));

        taskService.addTask(project, subprojectid, taskName);

        return "redirect:/projekt";
    }

    /**
     * @author Maja Bijedic
     * get taskid and delete it
     */
    @PostMapping("deleteTask")
    public String deleteTask(WebRequest request) throws ProjectManagerException {
        //Retrieve values from HTML form via WebRequest
        int taskid = Integer.parseInt(request.getParameter("taskid"));
        taskService.deleteTask(taskid);

        return "redirect:/projekt";
    }

    /**
     * @author Nicolai Okkels
     * set taskstatus depending on the current taskstatus, 0 = is finished, 1 is started and 2 is not started
     */
    @PostMapping("setTaskstatus")
    public String setTaskstatus(WebRequest request) throws ProjectManagerException {
        //Retrieve values from HTML form via WebRequest
        int taskid = Integer.parseInt(request.getParameter("taskid"));

        Task task = taskService.getTask(taskid);

        if (task.getTaskStatus() == 2) {
            task.setTaskStatus(1);
        } else if (task.getTaskStatus() == 1) {
            task.setTaskStatus(0);
        } else {
            task.setTaskStatus(2);
        }

        taskService.setTaskstatus(taskid, task.getTaskStatus());
        return "redirect:/projekt";
    }


    /**
     * @author Maja Bijedic
     * get taskid and edit the task
     */
    @PostMapping("editTask")
    public String editTask(WebRequest request) throws ProjectManagerException {
        //Retrieve values from HTML form via WebRequest
        String taskName = request.getParameter("taskName");
        int timeEstimate = Integer.parseInt(request.getParameter("timeEstimate"));
        String deadline = request.getParameter("deadline");
        int taskid = Integer.parseInt(request.getParameter("taskid"));

        taskService.editTask(taskid, taskName, timeEstimate, deadline);

        return "redirect:/projekt";
    }

}
