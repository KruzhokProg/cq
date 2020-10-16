package com.example.cq.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Matches {
    private List<Match> data;

    public Matches(List<Match> data) {
        this.data = data;
    }

    public List<Match> getData() {
        return data;
    }

    public void setData(List<Match> data) {
        this.data = data;
    }
}
