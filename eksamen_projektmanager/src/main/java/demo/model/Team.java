package demo.model;

import java.util.List;

public class Team {
    private String teamName;
    private List<User> team;

    public Team(String teamName, List<User> team) {
        this.teamName = teamName;
        this.team = team;
    }

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<User> getTeam() {
        return team;
    }

    public void setTeam(List<User> team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", team=" + team +
                '}';
    }
}