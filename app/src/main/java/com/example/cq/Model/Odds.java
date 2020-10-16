package com.example.cq.Model;

public class Odds {
    private TeamOdds team_1;
    private TeamOdds team_2;

    public Odds(TeamOdds team_1, TeamOdds team_2) {
        this.team_1 = team_1;
        this.team_2 = team_2;
    }

    public TeamOdds getTeam_1() {
        return team_1;
    }

    public void setTeam_1(TeamOdds team_1) {
        this.team_1 = team_1;
    }

    public TeamOdds getTeam_2() {
        return team_2;
    }

    public void setTeam_2(TeamOdds team_2) {
        this.team_2 = team_2;
    }
}
