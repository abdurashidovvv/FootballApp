package com.example.footballapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.network.ApiClient
import com.example.footballapp.network.ApiService
import com.example.footballapp.repository.MyFootballRepository
import com.example.footballapp.utils.MyData.API_KEY
import com.example.footballapp.utils.Status
import com.example.footballapp.viewmodel.MyFootballViewModel
import com.example.footballapp.viewmodel.MyViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var apiService: ApiService
    lateinit var myFootballViewModel: MyFootballViewModel
    lateinit var myFootballRepository: MyFootballRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = ApiClient.getApiService()
        myFootballRepository = MyFootballRepository(apiService)
        myFootballViewModel = ViewModelProvider(
            this, MyViewModelFactory(myFootballRepository)
        )[MyFootballViewModel::class.java]

        myFootballViewModel.getAllCountries().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "onCreate: $it")
                }
                Status.LOADING -> {
                    Log.d(TAG, "onCreate: Loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "onCreate: ${it.message}")
                }
            }
        }
    }
}