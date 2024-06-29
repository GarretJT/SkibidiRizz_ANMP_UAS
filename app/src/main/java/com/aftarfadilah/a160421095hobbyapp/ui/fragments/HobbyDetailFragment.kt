package com.aftarfadilah.a160421095hobbyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aftarfadilah.a160421095hobbyapp.databinding.FragmentHobbyDetailBinding
import com.aftarfadilah.a160421095hobbyapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

class HobbyDetailFragment : Fragment() {

    private lateinit var binding: FragmentHobbyDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHobbyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetchDetail(HobbyDetailFragmentArgs.fromBundle(requireArguments()).hobbyId.toInt())

            observeViewModel()
        }
    }

    fun observeViewModel(){
        viewModel.homesDetailLD.observe(viewLifecycleOwner, Observer {
            if(it == null){
                Toast.makeText(requireContext(), "Failed Detail Page", Toast.LENGTH_SHORT).show()
            }else{
                binding.hobby = it
            }
        })
    }
}