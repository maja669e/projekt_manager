package demo.controllers;

import demo.data.DataFacadeImpl;
import demo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


@Controller
public class UserController {

    private UserService userService = new UserService(new DataFacadeImpl());

    @ExceptionHandler(ProjectManagerException.class)
    @GetMapping("/")
    public String login(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws ProjectManagerException {
        //Retrieve values from HTML form via WebRequest
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = userService.Login(userName, password);
        setSessionInfo(request, user);
        return "redirect:/projekt_oversigt";
    }

    @GetMapping("/projekt_oversigt")
    public String displayProjects(WebRequest request, Model model) throws ProjectManagerException  {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        //Checks if user is logged in
        if (user == null) {
            return "redirect:/";
        } else {
            //Get all projects
            List<Project> projects = userService.getProjects(user.getUserid());
            model.addAttribute("projects", projects);
            return "projekt_oversigt";
        }
    }

    @PostMapping("addProject")
    public String addProject(WebRequest request) throws ProjectManagerException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        //Retrieve values from HTML form via WebRequest
        Project project = userService.addProject(user.getUserid());
        setSessionProject(request, project);

        return "redirect:/projekt";
    }

    @PostMapping("getProject")
    public String getProject(WebRequest request) throws ProjectManagerException {
        //Retrieve values from HTML form via WebRequest
        int projectid = Integer.parseInt(request.getParameter("projectid"));
        Project project = userService.getSingleProject(projectid);
        setSessionProject(request, project);

        return "redirect:/projekt";
    }

    @GetMapping("/projekt")
    public String project(WebRequest request,Model model) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Project project = (Project) request.getAttribute("project", WebRequest.SCOPE_SESSION);

        if (user == null) {
            return "redirect:/";
        } else {
            model.addAttribute("project", project);
            return "projekt";
        }
    }

    @PostMapping("getProjectTask")
    public String getProjectTask(){
        return "redirect:/project";
    }

    @PostMapping("addSubProject")
    public String addSubProject(WebRequest request) throws ProjectManagerException {
        Project project = (Project) request.getAttribute("project", WebRequest.SCOPE_SESSION);

        //Retrieve values from HTML form via WebRequest
        String subprojectName = request.getParameter("subprojectname");

        userService.addSubProject(project, subprojectName);

        return "redirect:/project";
    }

    @PostMapping("addTask")
    public String addTask(){
        return "redirect:/project";
    }


    private void setSessionProject(WebRequest request, Project project) {
        request.setAttribute("project", project, WebRequest.SCOPE_SESSION);
    }

    private void setSessionInfo(WebRequest request, User user) {
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }
}
