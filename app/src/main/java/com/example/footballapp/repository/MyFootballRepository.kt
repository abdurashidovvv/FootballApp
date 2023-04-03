package com.example.footballapp.repository

import com.example.footballapp.network.ApiService
import com.example.footballapp.utils.MyData.API_KEY

class MyFootballRepository(private val apiService: ApiService) {

    suspend fun getAllCountries() = apiService.getCountries(apiKey = API_KEY)

    suspend fun getAllCompetitions(action: String, country_id: Int, apiKey: String) =
        apiService.getAllCompetition(action = action, id = country_id, key = apiKey)

    suspend fun getStandings(action:String, league_id: Int, apiKey: String) =
        apiService.getLeague(action = action, league_id = league_id, key = apiKey)

    suspend fun getTeam(team_id: String, league_id: String, apiKey: String) =
        apiService.getTeam(team_id = team_id, league_id = league_id, apiKey = apiKey)
}