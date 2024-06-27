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
import com.aftarfadilah.a160421095hobbyapp.databinding.FragmentRegisBinding
import com.aftarfadilah.a160421095hobbyapp.model.User
import com.aftarfadilah.a160421095hobbyapp.viewmodel.UserViewModel
import com.aftarfadilah.a160421095hobbyapp.ui.activities.UserSignUpClickListener

class RegisFragment : Fragment(), UserSignUpClickListener {

    private lateinit var binding: FragmentRegisBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.user = User("", "", "", "") // Initialize with empty user object
        binding.addlistener = this // Set listener for sign-up button click
        binding.lifecycleOwner = viewLifecycleOwner // Set lifecycle owner for LiveData binding

        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onUserSignUpClick(v: View) {
        val username = binding.editTextUsername.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val firstName = binding.editTextFirstName.text.toString().trim()
        val lastName = binding.editTextLastName.text.toString().trim()

        if (username.isNotEmpty() && password.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty()) {
            val newUser = User(firstName, lastName, password, username)
            viewModel.register(newUser)
        } else {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }

        Navigation.findNavController(v).popBackStack()
    }
}
