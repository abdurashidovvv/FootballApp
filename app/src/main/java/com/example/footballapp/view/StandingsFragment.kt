package com.example.footballapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.footballapp.databinding.FragmentStandingsBinding
import com.example.footballapp.models.competitions.GetAllCompetitionsItem
import com.example.footballapp.network.ApiClient
import com.example.footballapp.network.ApiService
import com.example.footballapp.repository.MyFootballRepository
import com.example.footballapp.utils.MyData.API_KEY
import com.example.footballapp.utils.Status
import com.example.footballapp.viewmodel.MyFootballViewModel
import com.example.footballapp.viewmodel.MyViewModelFactory

class StandingsFragment : Fragment() {

    private val binding by lazy { FragmentStandingsBinding.inflate(layoutInflater) }
    private lateinit var apiService: ApiService
    private lateinit var myFootballViewModel: MyFootballViewModel
    private lateinit var myFootballRepository: MyFootballRepository
    private lateinit var list: ArrayList<GetAllCompetitionsItem>
    private val TAG = "StandingsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        apiService = ApiClient.getApiService()
        myFootballRepository = MyFootballRepository(apiService)
        myFootballViewModel = ViewModelProvider(
            this, MyViewModelFactory(myFootballRepository)
        )[MyFootballViewModel::class.java]

        list = ArrayList()

        val id = arguments?.getString("key")

        Log.d(TAG, "onCreateView: $id")
        myFootballViewModel.getAllCompetitions(id!!, API_KEY).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {

                    if (it.data != null) {
                        Log.d(TAG, "onCreateView: ${it.data}")
                        list.addAll(it.data)
                    } else {
                        Log.d(TAG, "onCreateView: ")
                    }
                    Log.d(TAG, "onCreateView: $list")
                }
                Status.ERROR -> {
                    Log.d(TAG, "onCreateView: ${it.message}")
                }
                Status.LOADING -> {
                    Log.d(TAG, "onCreateView: Loading")
                }
            }
        }

        return binding.root
    }
}