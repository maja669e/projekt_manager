package demo.service;

import demo.model.*;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    // facade to datasource layer
    private DataFacade facade = null;

    public TaskService(DataFacade facade) {
        this.facade = facade;
    }

    public void addMemberToTask(int taskid, int userid) throws ProjectManagerException {
        facade.addMemberToTask(taskid,userid);
    }

    public List<User> getTaskMembers(int taskid) throws ProjectManagerException{
        return facade.getTaskMembers(taskid);
    }

    public void deleteMemberFromTask(int taskid, int userid) throws ProjectManagerException {
        facade.deleteMemberFromTask(taskid, userid);
    }

    public void addTask(Project project, int subprojectid, String taskName) throws ProjectManagerException {
        Task task = new Task(LocalDate.now(), 0, taskName);
        for (SubProject subProject: project.getSubProjects()) {
            if(subProject.getSubProjectID() == subprojectid){
               subProject.getTasks().add(task);
            }
        }
        facade.addTask(project, subprojectid, taskName);
    }
    
    public void deleteTask(int taskid) throws ProjectManagerException {
        facade.deleteTask(taskid);
    }

    public void editTask(int taskid, String taskName, int timeEstimate, String deadline) throws ProjectManagerException {
        facade.editTask(taskid, taskName, timeEstimate, deadline);
    }

    public void setTaskstatus(int taskid, int taskstatus) throws ProjectManagerException {
        facade.setTaskstatus(taskid, taskstatus);
    }

    public Task getTask(int taskid) throws ProjectManagerException {
        return facade.getTask(taskid);
    }

    public User getTaskUser(String userName) throws ProjectManagerException {
        return facade.getTaskUser(userName);
    }
}
