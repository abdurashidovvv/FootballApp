package com.example.footballapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.RvItemBinding
import com.example.footballapp.models.competitions.GetAllCompetitionsItem
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.models.standings.GetStandingsItem
import com.example.footballapp.utils.MyData.API_KEY
import com.example.footballapp.utils.Status
import com.example.footballapp.view.ClubRvAdapter
import com.example.footballapp.viewmodel.MyFootballViewModel
import com.squareup.picasso.Picasso

class StandingsRvAdapter(
    private val list: List<GetAllCountriesItem>,
    val rvClick: RvClick
) :
    RecyclerView.Adapter<StandingsRvAdapter.Vh>() {

    private lateinit var clubRvAdapter: ClubRvAdapter

    inner class Vh(private val rvItemBinding: RvItemBinding) : RecyclerView.ViewHolder(rvItemBinding.root) {
        fun onBind(getAllCountriesItem: GetAllCountriesItem) {
            if (getAllCountriesItem.country_logo!=""){
                Picasso.get().load(getAllCountriesItem.country_logo).into(rvItemBinding.countryFlagImg)
            }

            rvItemBinding.leagueNameTv.text = getAllCountriesItem.country_name
            rvItemBinding.root.setOnClickListener {
                rvClick.onClick(getAllCountriesItem)
            }

//            val leagues =ArrayList<GetAllCompetitionsItem>()
//            viewModel.getAllCompetitions(list[0].country_id, API_KEY).observe(lifecycleOwner){
//                when(it.status){
//                    Status.SUCCESS->{
//                        leagues.addAll(it.data!!)
//                    }
//                    Status.LOADING->{}
//                    Status.ERROR->{}
//                }
//            }
//            val list=ArrayList<GetStandingsItem>()
//            viewModel.getLeagues(leagues[2].league_id, API_KEY).observe(lifecycleOwner){
//                when(it.status){
//                    Status.SUCCESS->{
//                        list.addAll(it.data!!)
//                    }
//                    Status.LOADING->{}
//                    Status.ERROR->{}
//                }
//            }
//            clubRvAdapter = ClubRvAdapter(list)
//            rvItemBinding.innerTableRv.adapter = clubRvAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface RvClick{
        fun onClick(getAllCountriesItem: GetAllCountriesItem)
    }
}