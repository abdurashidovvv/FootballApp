package com.example.footballapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.ClubRvItemBinding
import com.example.footballapp.models.standings.GetStandingsItem
import com.example.footballapp.models.teams.GetTeam
import com.squareup.picasso.Picasso

class ClubRvAdapter(private val list: List<GetStandingsItem>) :
    RecyclerView.Adapter<ClubRvAdapter.Vh>() {

    inner class Vh(private val clubRvItemBinding: ClubRvItemBinding) :
        RecyclerView.ViewHolder(clubRvItemBinding.root) {
        fun onBind(getStandingsItem: GetStandingsItem) {
            Picasso.get().load(getStandingsItem.team_badge).into(clubRvItemBinding.clubFlagImg)
            clubRvItemBinding.clubNameTv.text = getStandingsItem.team_name
            clubRvItemBinding.gamePlayed.text = getStandingsItem.overall_league_GA
            clubRvItemBinding.pts.text = getStandingsItem.overall_league_PTS
            clubRvItemBinding.draw.text = getStandingsItem.overall_league_D
            clubRvItemBinding.losses.text = getStandingsItem.overall_league_W
            clubRvItemBinding.wins.text = getStandingsItem.overall_league_W
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ClubRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}