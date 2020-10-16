package com.example.cq.Network;

import com.example.cq.Model.Match;
import com.example.cq.Model.Matches;
import com.example.cq.Model.MatchesObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {

    @GET("series")
    Call<Matches> getMatches(@Header("Authorization") String authHeader);
}
