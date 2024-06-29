package com.aftarfadilah.a160421095hobbyapp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aftarfadilah.a160421095hobbyapp.databinding.HobbyListItemBinding
import com.aftarfadilah.a160421095hobbyapp.model.Hobby
import com.aftarfadilah.a160421095hobbyapp.ui.activities.ButtonActionNavClickListener
import com.squareup.picasso.Picasso

class HobbyListViewAdapter (val homeList: ArrayList<Hobby>)
    :RecyclerView.Adapter<HobbyListViewAdapter.HomeViewHolder>(), ButtonActionNavClickListener {

    class HomeViewHolder(var binding: HobbyListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HobbyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.hobby = homeList[position]
        holder.binding.navListener = this
    }

    fun updateHomeList(newHomeList: List<Hobby>){
        homeList.clear()
        homeList.addAll(newHomeList)
        notifyDataSetChanged()
    }

    override fun onButtonActionNavClick(v: View) {
        val id = v.tag.toString()
        val action = HomeFragmentDirections.toDetail(id)
        Navigation.findNavController(v).navigate(action)
    }


}