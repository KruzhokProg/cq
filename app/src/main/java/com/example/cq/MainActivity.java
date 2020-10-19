package com.example.cq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.example.cq.Adapter.MatchAdapter;
import com.example.cq.Model.Match;
import com.example.cq.Model.Matches;
import com.example.cq.Model.MatchesObject;
import com.example.cq.Network.ApiClient;
import com.example.cq.Network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    MatchAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_matches);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String userName = "EM_dy3YY2h2UQR3";
        String password = "";
        String base = userName + ":" + password;
        final String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        Call<Matches> call = apiService.getMatches(authHeader);
        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Matches data = response.body();
                MatchAdapter adapter = new MatchAdapter(data.getData(),  getBaseContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<Matches> call = apiService.getMatches(authHeader);
                call.enqueue(new Callback<Matches>() {
                    @Override
                    public void onResponse(Call<Matches> call, Response<Matches> response) {
                        Matches data = response.body();
                        MatchAdapter adapter = new MatchAdapter(data.getData(),  getBaseContext());
                        rv.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Matches> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                swipeRefreshLayout.setRefreshing(false);
            }
        });





    }
}
