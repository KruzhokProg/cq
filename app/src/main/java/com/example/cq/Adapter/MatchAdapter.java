package com.example.cq.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cq.Model.Match;
import com.example.cq.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MatchAdapter extends RecyclerView.Adapter<MatchViewHolder> {

    List<Match> matches;
    Context context;

    public MatchAdapter(List<Match> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {

        // звезды


        // коэффициенты
        if(matches.get(position).getOdds()!= null) {
            holder.kof1.setVisibility(View.VISIBLE);
            holder.kof1.setText(matches.get(position).getOdds().getTeam_1().getOdd());
        }
        else{
            holder.kof1.setVisibility(View.INVISIBLE);
        }
        if(matches.get(position).getOdds()!= null) {
            holder.kof2.setVisibility(View.VISIBLE);
            holder.kof2.setText(matches.get(position).getOdds().getTeam_2().getOdd());
        }
        else{
            holder.kof2.setVisibility(View.INVISIBLE);
        }

        // названия команд
        holder.team1Name.setText(matches.get(position).getTeam_1().getShort_name());
        holder.team2Name.setText(matches.get(position).getTeam_2().getShort_name());
        holder.shortTitle.setText(matches.get(position).getTournament().getShort_title());
        holder.bof.setText(String.valueOf(matches.get(position).getBestOf()));

        // статус матча
        if(matches.get(position).getDate_end()==null) {
            holder.matchStatus.setText(matches.get(position).getDate_start());
        }else{
            holder.matchStatus.setText("Завершен");
        }


        // счет
        holder.score.setTextColor(Color.parseColor("#7F8497"));
        Typeface typeface = ResourcesCompat.getFont(context, R.font.alfa_slab_one);
        holder.score.setTypeface(typeface);
        if( matches.get(position).getDate_end() != null) {
            holder.score.setText(String.valueOf(matches.get(position).getScore_1()) + " : " + String.valueOf(matches.get(position).getScore_2()));
        }
        else{
            String dateMatch = matches.get(position).getDate_start().split("T")[0];
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String dateNow = dateFormat.format(currentDate);
            
            if(dateMatch.equals(dateNow)){
                holder.score.setTextColor(Color.parseColor("#FA4B37"));
                holder.score.setText("LIVE");
            }
            else {
                holder.score.setText("VS");
            }
        }

        // логотип команд
        Glide.with(holder.itemView.getContext())
                .load(matches.get(position).getTeam_1().getImage())
                .into(holder.logoTeam1);

        Glide.with(holder.itemView.getContext())
                .load(matches.get(position).getTeam_2().getImage())
                .into(holder.logoTeam2);

        // логотип игры
        switch (matches.get(position).getGame()) {
            case "CS:GO":
                holder.logoGame.setImageResource(R.drawable.csgo);
                break;
            case "Dota 2":
                holder.logoGame.setImageResource(R.drawable.dota2);
                break;
            case "LoL":
                holder.logoGame.setImageResource(R.drawable.lol);
                break;
            case "Overwatch":
                holder.logoGame.setImageResource(R.drawable.overwatch);
                break;
            case "Valorant":
                holder.logoGame.setImageResource(R.drawable.valorant);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
