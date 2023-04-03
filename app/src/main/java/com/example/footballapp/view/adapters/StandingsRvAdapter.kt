package com.example.footballapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.databinding.RvItemBinding
import com.example.footballapp.models.competitions.GetAllCompetitionsItem
import com.example.footballapp.models.countries.GetAllCountriesItem
import com.example.footballapp.utils.MyData.API_KEY
import com.example.footballapp.utils.Status
import com.example.footballapp.view.ClubRvAdapter
import com.squareup.picasso.Picasso

class StandingsRvAdapter(
    private val list: List<com.example.footballapp.models.Result>,
    val rvClick: RvClick
) :
    RecyclerView.Adapter<StandingsRvAdapter.Vh>() {

    private lateinit var clubRvAdapter: ClubRvAdapter

    inner class Vh(private val rvItemBinding: RvItemBinding) : RecyclerView.ViewHolder(rvItemBinding.root) {
        fun onBind(result: com.example.footballapp.models.Result) {
            Picasso.get().load(result.country_id.country_logo).into(rvItemBinding.countryFlagImg)
            rvItemBinding.countryTv.text=result.country_id.country_name
            clubRvAdapter=ClubRvAdapter(result.standing)
            rvItemBinding.innerTableRv.adapter=clubRvAdapter
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