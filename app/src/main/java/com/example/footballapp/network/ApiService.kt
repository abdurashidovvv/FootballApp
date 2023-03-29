package com.example.footballapp.network

import com.example.footballapp.models.competitions.GetAllCompetitionsItem
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.models.standings.GetStandingsItem
import com.example.footballapp.models.teams.GetTeamItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/")
    suspend fun getCountries(
        @Query("action") action: String = "get_countries",
        @Query("apiKey") apiKey: String,
    ): Response<List<GetAllCountriesItem>>

    @GET("/")
    suspend fun getAllCompetition(
        @Query("action") action: String = "get_leagues",
        @Query("country_id") country_id: String,
        @Query("apiKey") apiKey: String,
    ): Response<List<GetAllCompetitionsItem>>

    @GET("/")
    suspend fun getLeague(
        @Query("action") action: String = "get_standings",
        @Query("league_id") league_id: String,
        @Query("apiKey") apiKey: String,
    ): Response<List<GetStandingsItem>>

    @GET("/")
    suspend fun getTeam(
        @Query("action") action: String = "get_teams",
        @Query("team_id") team_id: String,
        @Query("league_id") league_id: String,
        @Query("apiKey") apiKey: String,
    ): Response<List<GetTeamItem>>
}

