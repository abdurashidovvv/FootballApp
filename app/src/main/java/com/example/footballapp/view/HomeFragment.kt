package com.example.footballapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.R
import com.example.footballapp.databinding.FragmentHomeBinding
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.network.ApiClient
import com.example.footballapp.network.ApiService
import com.example.footballapp.repository.MyFootballRepository
import com.example.footballapp.utils.Status
import com.example.footballapp.view.adapters.StandingsRvAdapter
import com.example.footballapp.viewmodel.MyFootballViewModel
import com.example.footballapp.viewmodel.MyViewModelFactory

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    lateinit var apiService: ApiService
    lateinit var myFootballViewModel: MyFootballViewModel
    lateinit var myFootballRepository: MyFootballRepository
    private lateinit var standingsRvAdapter: StandingsRvAdapter
    private lateinit var list:ArrayList<GetAllCountriesItem>

    private val TAG = "HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        list= ArrayList()
        apiService = ApiClient.getApiService()
        myFootballRepository = MyFootballRepository(apiService)
        myFootballViewModel = ViewModelProvider(
            this, MyViewModelFactory(myFootballRepository)
        )[MyFootballViewModel::class.java]

        myFootballViewModel.getAllCountries().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 -> list.addAll(it1) }
                    Log.d(TAG, "onCreate: $it")
                }
                Status.LOADING -> {
                    Log.d(TAG, "onCreate: Loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "onCreate: Error")
                }
            }
        }
        standingsRvAdapter= StandingsRvAdapter(list, myFootballViewModel)
        binding.myRv.adapter=standingsRvAdapter
        return binding.root
    }
}