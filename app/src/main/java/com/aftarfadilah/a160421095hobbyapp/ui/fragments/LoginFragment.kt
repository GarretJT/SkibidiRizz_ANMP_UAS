package com.aftarfadilah.a160421095hobbyapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aftarfadilah.a160421095hobbyapp.databinding.FragmentLoginBinding
import com.aftarfadilah.a160421095hobbyapp.model.User
import com.aftarfadilah.a160421095hobbyapp.viewmodel.UserViewModel
import com.aftarfadilah.a160421095hobbyapp.ui.activities.UserLoginClickListener

class LoginFragment : Fragment(), UserLoginClickListener {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.txtRegis.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginRegis()
            Navigation.findNavController(it).navigate(action)
        }

        binding.user = User("", "", "", "") // Initialize user for data binding
        binding.loginListener = this // Set listener for login button click
        binding.lifecycleOwner = viewLifecycleOwner // Important for LiveData

        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()

                // Navigate to another screen after successful login
                val action = LoginFragmentDirections.actionLoginProfile(user.id)
                Navigation.findNavController(binding.root).navigate(action)
            } else {
                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onUserLoginClick(v: View) {
        val username = binding.txtUsername.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            try {
                viewModel.login(username, password)
            } catch (e: Exception) {
                Toast.makeText(context, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
}
