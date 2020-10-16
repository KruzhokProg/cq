package com.example.cq.Model;

public class Tournament {
    private String title;
    private String short_title;
    private String url;
    private String image;
    private String date_start;
    private String date_end;

    public Tournament(String title, String short_title, String url, String image, String date_start, String date_end) {
        this.title = title;
        this.short_title = short_title;
        this.url = url;
        this.image = image;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
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
}
