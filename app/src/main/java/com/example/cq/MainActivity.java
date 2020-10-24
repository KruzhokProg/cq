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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    MatchAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ApiInterface apiService;
    String authHeader;
    Calendar selectedDate;
    HorizontalCalendar horizontalCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_matches);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        String userName = "EM_dy3YY2h2UQR3";
        String password = "";
        String base = userName + ":" + password;
        authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        selectedDate = Calendar.getInstance(TimeZone.getDefault());
        //horizontalCalendar.selectDate(nowDate, true);
        UpdateRV(selectedDate);

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDate = date;
                UpdateRV(date);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                UpdateRV(selectedDate);
            }
        });

    }

    public void UpdateRV(final Calendar date){

        Call<Matches> call = apiService.getMatches(authHeader);
        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Matches data = response.body();
                List<Match> list = data.getData();
                List<Match> res = new ArrayList<>();
                for (Match match : list) {
                    String match_date = (match.getDate_start()).split("T")[0];
                    String[] dateMas = match_date.split("-");
                    int year = Integer.parseInt(dateMas[0]);
                    int month = Integer.parseInt(dateMas[1]);
                    int day = Integer.parseInt(dateMas[2]);
                    int selectedDay = date.get(Calendar.DAY_OF_MONTH);
                    int selectedMonth = date.get(Calendar.MONTH);
                    int selectedYear = date.get(Calendar.YEAR);
                    if(year == selectedYear && month == (selectedMonth + 1) && day == selectedDay){
                        res.add(match);
                    }
                }
                adapter = new MatchAdapter(res, getBaseContext());
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout.setRefreshing(false);
    }
}
