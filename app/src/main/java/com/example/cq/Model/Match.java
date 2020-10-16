package com.example.cq.Model;

public class Match {

    private String title;
    private String game;
    private int tier;
    private int bestOf;
    private String date_start;
    private String date_end;
    private int score_1;
    private int score_2;
    private Tournament tournament;
    private Team team_1;
    private Team team_2;
    private Odds odds;
    private String url;
    private String image;

    public Match(String title, String game, int tier, int bestOf, String date_start, String date_end, int score_1, int score_2, Tournament tournament, Team team_1, Team team_2, Odds odds, String url, String image) {
        this.title = title;
        this.game = game;
        this.tier = tier;
        this.bestOf = bestOf;
        this.date_start = date_start;
        this.date_end = date_end;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.tournament = tournament;
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.odds = odds;
        this.url = url;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getBestOf() {
        return bestOf;
    }

    public void setBestOf(int bestOf) {
        this.bestOf = bestOf;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public int getScore_1() {
        return score_1;
    }

    public void setScore_1(int score_1) {
        this.score_1 = score_1;
    }

    public int getScore_2() {
        return score_2;
    }

    public void setScore_2(int score_2) {
        this.score_2 = score_2;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getTeam_1() {
        return team_1;
    }

    public void setTeam_1(Team team_1) {
        this.team_1 = team_1;
    }

    public Team getTeam_2() {
        return team_2;
    }

    public void setTeam_2(Team team_2) {
        this.team_2 = team_2;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
