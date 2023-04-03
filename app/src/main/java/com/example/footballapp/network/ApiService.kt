package com.example.footballapp.network

import com.example.footballapp.models.competitions.GetAllCompetitions
import com.example.footballapp.models.competitions.GetAllCompetitionsItem
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.models.standings.GetStandingsItem
import com.example.footballapp.models.teams.GetTeamItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("countries")
    suspend fun getCountries(
        @Query("APIkey") apiKey: String,
        @Query("action") action: String = "get_countries",
    ): List<GetAllCountriesItem>

    @GET("/")
    suspend fun getAllCompetition(
        @Query("action") action: String = "get_leagues",
        @Query("country_id") id: Int = 6,
        @Query("APIkey") key: String = "xxx",
    ): List<GetAllCompetitionsItem>

    @GET("/")
    suspend fun getLeague(
        @Query("action") action: String = "get_standings",
        @Query("league_id") league_id: Int = 302,
        @Query("APIkey") key: String = "xxx",
    ): List<GetStandingsItem>

    @GET("/")
    suspend fun getTeam(
        @Query("action") action: String = "get_teams",
        @Query("team_id") team_id: String,
        @Query("league_id") league_id: String,
        @Query("apiKey") apiKey: String,
    ): Response<List<GetTeamItem>>
}

