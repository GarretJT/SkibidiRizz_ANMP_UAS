package com.aftarfadilah.a160421095hobbyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aftarfadilah.a160421095hobbyapp.R
import com.aftarfadilah.a160421095hobbyapp.databinding.FragmentHomeBinding
import com.aftarfadilah.a160421095hobbyapp.viewmodel.ListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private lateinit var binding: FragmentHomeBinding
    private val hobbyListAdapter = HobbyListViewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.ItemHome)
            }
        })

        viewModel =ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = hobbyListAdapter

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility =View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility =View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.homesLD.observe(viewLifecycleOwner, Observer { hobbyListAdapter.updateHomeList(it) })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            }
            else{
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.homesLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtError.visibility = View.VISIBLE
            }
            else{
                binding.txtError.visibility = View.GONE
            }
        })
    }

}