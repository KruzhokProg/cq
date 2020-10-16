package com.example.cq.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cq.R;

public class MatchViewHolder extends RecyclerView.ViewHolder {

    public ImageView star1, star2, star3, logoGame, logoTeam1, logoTeam2, imgv_cup;
    public TextView shortTitle, bof, matchStatus, score, team1Name, team2Name, kof1, kof2;

    public MatchViewHolder(@NonNull View itemView) {
        super(itemView);

        star1 = itemView.findViewById(R.id.star1);
        star2 = itemView.findViewById(R.id.star2);
        star3 = itemView.findViewById(R.id.star3);
        imgv_cup = itemView.findViewById(R.id.imgv_cup);
        logoGame = itemView.findViewById(R.id.imgv_game);
        logoTeam1 = itemView.findViewById(R.id.imgv_team1);
        logoTeam2 = itemView.findViewById(R.id.imgv_team2);
        shortTitle = itemView.findViewById(R.id.short_title);
        bof = itemView.findViewById(R.id.bof);
        matchStatus = itemView.findViewById(R.id.match_status);
        score = itemView.findViewById(R.id.tv_score);
        team1Name = itemView.findViewById(R.id.team1_name);
        team2Name = itemView.findViewById(R.id.team2_name);
        kof1 = itemView.findViewById(R.id.tvKof1);
        kof2 = itemView.findViewById(R.id.tvKof2);
    }
}
