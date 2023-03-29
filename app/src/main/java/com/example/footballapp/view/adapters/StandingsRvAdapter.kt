package com.example.footballapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.RvItemBinding
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.utils.MyData.API_KEY
import com.example.footballapp.view.ClubRvAdapter
import com.example.footballapp.viewmodel.MyFootballViewModel
import com.squareup.picasso.Picasso

class StandingsRvAdapter(
    private val list: List<GetAllCountriesItem>,
    private val viewModel: MyFootballViewModel,
) :
    RecyclerView.Adapter<StandingsRvAdapter.Vh>() {

    private lateinit var clubRvAdapter: ClubRvAdapter

    inner class Vh(private val rvItemBinding: RvItemBinding) :
        RecyclerView.ViewHolder(rvItemBinding.root) {
        fun onBind(getAllCountriesItem: GetAllCountriesItem) {
            Picasso.get().load(getAllCountriesItem.country_logo).into(rvItemBinding.countryFlagImg)
            rvItemBinding.countryTv.text = getAllCountriesItem.country_name
            val leagues = viewModel.getAllCompetitions(list[0].country_id, API_KEY)
            val list = viewModel.getLeagues(leagues.value?.data!![2].league_id, API_KEY)
            clubRvAdapter = ClubRvAdapter(list.value?.data!!)
            rvItemBinding.innerTableRv.adapter = clubRvAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}