package com.aftarfadilah.a160421095hobbyapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aftarfadilah.a160421095hobbyapp.R
import com.aftarfadilah.a160421095hobbyapp.databinding.FragmentProfileBinding
import com.aftarfadilah.a160421095hobbyapp.viewmodel.UserViewModel
import com.aftarfadilah.a160421095hobbyapp.ui.activities.ProfileUpdateClickListener

class ProfileFragment : Fragment(), ProfileUpdateClickListener {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel
    private val args: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.userViewModel = viewModel

        val userId = args.userId
        viewModel.fetch(userId)

        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.user = user
            }
        })

        binding.profileClickListener = this
    }

    override fun onChangeUsernameClick(v: View) {
        val newUsername = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (newUsername.isNotEmpty() && password == binding.user?.password) {
            binding.user?.username = newUsername
            viewModel.changeUsername(newUsername, password)
            Toast.makeText(context, "Username updated successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Incorrect password or empty username", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDeleteAccountClick(v: View) {
        viewModel.deleteUser()
        Toast.makeText(context, "Account deleted successfully", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.actionProfileLogin)
    }

    override fun onLogOutClick(v: View) {
        viewModel.logoutUser()
        Toast.makeText(context, "Logged out successfully", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.actionProfileLogin)
    }

}
