package com.example.footballapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.models.competitions.GetAllCompetitionsItem
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.models.standings.GetStandingsItem
import com.example.footballapp.models.teams.GetTeamItem
import com.example.footballapp.repository.MyFootballRepository
import com.example.footballapp.utils.Resource
import com.example.footballapp.utils.Status
import kotlinx.coroutines.launch

class MyFootballViewModel(private val myFootballRepository: MyFootballRepository) : ViewModel() {

    private val liveData = MutableLiveData<Resource<List<GetAllCountriesItem>>>()


    fun getAllCountries(): MutableLiveData<Resource<List<GetAllCountriesItem>>> {
        liveData.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch {
            try {
                liveData.postValue(Resource(Status.SUCCESS,
                    myFootballRepository.getAllCountries().body(),
                    "Success"))
            } catch (e: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, "${e.message}"))
            }
        }
        return liveData
    }

    private val competitionsLiveData = MutableLiveData<Resource<List<GetAllCompetitionsItem>>>()
    fun getAllCompetitions(
        country_id: String,
        apiKey: String,
    ): MutableLiveData<Resource<List<GetAllCompetitionsItem>>> {
        liveData.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch {
            try {
                competitionsLiveData.postValue(Resource(Status.SUCCESS,
                    myFootballRepository.getAllCompetitions(country_id, apiKey).body(),
                    "Success"))
            } catch (e: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, "Error"))
            }
        }
        return competitionsLiveData
    }

    private val leagueLiveData = MutableLiveData<Resource<List<GetStandingsItem>>>()
    fun getLeagues(
        league_id: String,
        apiKey: String,
    ): MutableLiveData<Resource<List<GetStandingsItem>>> {
        leagueLiveData.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch {
            try {
                leagueLiveData.postValue(Resource(Status.SUCCESS,
                    myFootballRepository.getStandings(league_id, apiKey).body(),
                    "Success"))
            } catch (e: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, "${e.message}"))
            }
        }
        return leagueLiveData
    }


    private val teamLiveData = MutableLiveData<Resource<List<GetTeamItem>>>()
    fun getTeam(
        team_id: String,
        league_id: String,
        apiKey: String,
    ): MutableLiveData<Resource<List<GetTeamItem>>> {
        teamLiveData.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch {
            try {
                teamLiveData.postValue(Resource(Status.SUCCESS,
                    myFootballRepository.getTeam(team_id, league_id, apiKey).body(),
                    "Success"))
            } catch (e: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, "Error"))
            }
        }
        return teamLiveData
    }
}