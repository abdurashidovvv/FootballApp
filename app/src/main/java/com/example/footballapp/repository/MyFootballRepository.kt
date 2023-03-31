package com.example.footballapp.repository

import com.example.footballapp.network.ApiService
import com.example.footballapp.utils.MyData.API_KEY

class MyFootballRepository(private val apiService: ApiService) {

    suspend fun getAllCountries() = apiService.getCountries(apiKey = API_KEY)

    suspend fun getAllCompetitions(action:String, country_id: Int, apiKey: String) =
        apiService.getAllCompetition(action = action, id = country_id, key = apiKey)

    suspend fun getStandings(league_id: String, apiKey: String) =
        apiService.getLeague(league_id = league_id, apiKey = apiKey)

    suspend fun getTeam(team_id: String, league_id: String, apiKey: String) =
        apiService.getTeam(team_id = team_id, league_id = league_id, apiKey = apiKey)
}