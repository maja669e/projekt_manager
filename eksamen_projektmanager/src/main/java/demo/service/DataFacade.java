package demo.service;

import demo.model.*;

import java.util.List;


/**
 * @author Nicolai Okkels
 * @author Phuc Nguyen
 * @author Maja Bijedic
 */
public interface DataFacade {

    public void addProject(Project project, int userid) throws ProjectManagerException;
    public User Login(String userName, String password) throws ProjectManagerException;
    public List<Project> getProjects(int userid) throws ProjectManagerException ;
    public Project getProject(int projectid) throws ProjectManagerException;
    public void addSubProject(Project project) throws ProjectManagerException;
    public void editProject(int projectid, String newProjectName, String enddate) throws ProjectManagerException;
    public void changeSubProjectName(int subProjectid, String newSubProjectName) throws ProjectManagerException;
    public void deleteSubproject(int subprojectid)throws ProjectManagerException;
    public void deleteProject(int projectid)throws ProjectManagerException;
    public void addTask(Project project, int subprojectid, String taskName) throws ProjectManagerException;
    public void deleteTask(int taskid) throws ProjectManagerException;
    public void editTask(int taskid, String taskName, int timeEstimate, String deadline) throws ProjectManagerException;
    public void setTaskstatus(int taskid, int taskstatus) throws ProjectManagerException;
    public Task getTask(int taskid) throws ProjectManagerException;
    public Team getTeam(int teamid) throws ProjectManagerException;
    public int getUserTeamId(int userid) throws ProjectManagerException;
    public User getTaskUser(String userName) throws ProjectManagerException;
    public void addMemberToTask(int taskid, int userid) throws ProjectManagerException;
    public List<User> getTaskMembers(int taskid) throws ProjectManagerException;
    public void deleteMemberFromTask(int taskid, int userid) throws ProjectManagerException;
}
