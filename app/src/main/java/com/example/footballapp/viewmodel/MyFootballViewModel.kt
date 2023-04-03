package com.example.footballapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapp.models.competitions.GetAllCompetitions
import com.example.footballapp.models.competitions.GetAllCompetitionsItem
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.models.standings.GetStandingsItem
import com.example.footballapp.models.teams.GetTeamItem
import com.example.footballapp.repository.MyFootballRepository
import com.example.footballapp.utils.Resource
import com.example.footballapp.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MyFootballViewModel(private val myFootballRepository: MyFootballRepository) : ViewModel() {

    private val liveData = MutableLiveData<Resource<List<GetAllCountriesItem>>>()
    private val TAG = "MyFootballViewModel"


    fun getAllCountries(): MutableLiveData<Resource<List<GetAllCountriesItem>>> {
        liveData.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch {
            try {
                liveData.postValue(Resource(Status.SUCCESS,
                    myFootballRepository.getAllCountries(),
                    "Success"))
            } catch (e: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, "${e.message}"))
            }
        }
        return liveData
    }


    private val competitionsLiveData = MutableLiveData<Resource<List<GetAllCompetitionsItem>>>()
    fun getAllCompetitions(
        action: String,
        country_id: Int,
        apiKey: String,
    ): MutableLiveData<Resource<List<GetAllCompetitionsItem>>> {
        competitionsLiveData.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                competitionsLiveData.postValue(Resource(Status.SUCCESS,
                    myFootballRepository.getAllCompetitions(action, country_id, apiKey),
                    "Success"))
            } catch (e: Exception) {
                competitionsLiveData.postValue(Resource(Status.ERROR, null, e.message))
            }
        }
        return competitionsLiveData
    }


    //Get Standings
    private val leagueLiveData = MutableLiveData<Resource<List<GetStandingsItem>>>()
    fun getLeagues(
        action: String,
        league_id: Int,
        apiKey: String
    ): MutableLiveData<Resource<List<GetStandingsItem>>> {
        leagueLiveData.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                leagueLiveData.postValue(Resource(Status.SUCCESS,
                    myFootballRepository.getStandings(action,league_id, apiKey),
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