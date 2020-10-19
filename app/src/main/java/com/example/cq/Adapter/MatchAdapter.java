package com.example.cq.Adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.cq.MainActivity;
import com.example.cq.Model.Match;
import com.example.cq.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

public class MatchAdapter extends RecyclerView.Adapter<MatchViewHolder>{

    List<Match> matches;
    Context context;
    private RequestBuilder<PictureDrawable> requestBuilder;

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

        // сегодняшняя дата
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateNow = LocalDateTime.now();
        // звезды
        switch (matches.get(position).getTier()){
            case 1:
                holder.goldStars.setVisibility(View.VISIBLE);
                holder.silverStars.setVisibility(View.INVISIBLE);
                holder.bronzeStars.setVisibility(View.INVISIBLE);
                break;
            case 2:
                holder.goldStars.setVisibility(View.INVISIBLE);
                holder.silverStars.setVisibility(View.VISIBLE);
                holder.bronzeStars.setVisibility(View.INVISIBLE);
                break;
            case 3:
                holder.goldStars.setVisibility(View.INVISIBLE);
                holder.silverStars.setVisibility(View.INVISIBLE);
                holder.bronzeStars.setVisibility(View.VISIBLE);
                break;
        }

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


        // счет и статус матча
        holder.score.setTextColor(Color.parseColor("#7F8497"));
        Typeface typeface = ResourcesCompat.getFont(context, R.font.alfa_slab_one);
        holder.score.setTypeface(typeface);
        if( matches.get(position).getDate_end() != null) {
            holder.score.setText(String.valueOf(matches.get(position).getScore_1()) + " : " + String.valueOf(matches.get(position).getScore_2()));
            holder.matchStatus.setText("Завершен");
        }
        else{
            String date = matches.get(position).getDate_start().split("[T.]")[0];
            String time = matches.get(position).getDate_start().split("[T.]")[1];
            String fullDateMatch = date + " " + time;
            LocalDateTime dateMatch = LocalDateTime.parse(fullDateMatch, formatter);

            Duration duration = Duration.between(dateMatch, dateNow);
            if(duration.isNegative()){
                holder.score.setTextColor(Color.parseColor("#FA4B37"));
                holder.score.setText("LIVE");
                holder.matchStatus.setText("Идет игра");
            }
            else {
                holder.score.setText("VS");

                long days = duration.toDays();
                long hours = duration.toHours()%24;
                long minutes = duration.toMinutes()%60;

                if(days > 0) {
                    holder.matchStatus.setText("через " + String.valueOf(days) + " д. в " + String.valueOf(hours) + ":" + String.valueOf(minutes));
                }
                else if(hours > 0){
                    if(minutes/10 == 0) {
                        holder.matchStatus.setText("через " + String.valueOf(hours) + " ч. в " + String.valueOf(hours) + ":0" + String.valueOf(minutes));
                    }
                    else{
                        holder.matchStatus.setText("через " + String.valueOf(hours) + " ч. в " + String.valueOf(hours) + ":" + String.valueOf(minutes));
                    }
                }
                else{
                    if(minutes/10 == 0) {
                        holder.matchStatus.setText("через " + String.valueOf(minutes) + " м. в " + String.valueOf(hours) + ":0" + String.valueOf(minutes));
                    }
                    else{
                        holder.matchStatus.setText("через " + String.valueOf(minutes) + " м. в " + String.valueOf(hours) + ":" + String.valueOf(minutes));
                    }
                }
            }
        }

        // логотип команд

        String[] team1_image_parts = matches.get(position).getTeam_1().getImage().split("[.]");
        String team1_image_format = team1_image_parts[team1_image_parts.length-1];
        String[] team2_image_parts = matches.get(position).getTeam_2().getImage().split("[.]");
        String team2_image_format = team2_image_parts[team2_image_parts.length-1];

        if (team1_image_format.equals("svg")){
            Uri uri = Uri.parse(matches.get(position).getTeam_1().getImage());
            GlideToVectorYou
                    .init()
                    .with(context)
                    .load(uri, holder.logoTeam1);
        }
        else{
            Glide.with(holder.itemView.getContext())
                    .load(matches.get(position).getTeam_1().getImage())
                    .into(holder.logoTeam1);
        }

        if (team2_image_format.equals("svg")){
            Uri uri = Uri.parse(matches.get(position).getTeam_2().getImage());
            GlideToVectorYou
                    .init()
                    .with(context)
                    .load(uri, holder.logoTeam2);
        }
        else{
            Glide.with(holder.itemView.getContext())
                    .load(matches.get(position).getTeam_2().getImage())
                    .into(holder.logoTeam2);
        }

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
