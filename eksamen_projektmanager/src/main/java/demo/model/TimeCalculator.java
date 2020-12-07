package demo.model;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class TimeCalculator {

    public int calSubProjectTotalTime(SubProject subProject) { //all subprojects for a single project

        int totalTime = 0;
        for (int i = 0; i < subProject.getTasks().size(); i++) {
            totalTime += subProject.getTasks().get(i).getTimeEstimation();
        }

        return totalTime;
    }

    public int calProjectTotalTime(Project project) {

        int totalTime = 0;

        for (int i = 0; i < project.getSubProjects().size(); i++) {
            totalTime += calSubProjectTotalTime(project.getSubProjects().get(i));
        }

        return totalTime;
    }

    public int calWorkHoursPerProject (Project project){

        int totalProjectHours = calProjectTotalTime(project);

        int dif = (int) ChronoUnit.DAYS.between(project.getExpStartDate(),project.getExpEndDate());
        dif = Math.abs(dif);

        int workHoursPerDay = totalProjectHours / dif;

        return workHoursPerDay;
    }

}