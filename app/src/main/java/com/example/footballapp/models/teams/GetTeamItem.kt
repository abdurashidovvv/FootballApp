package com.example.footballapp.models.teams

data class GetTeamItem(
    val coaches: List<Coache>,
    val players: List<Player>,
    val team_badge: String,
    val team_key: String,
    val team_name: String
)