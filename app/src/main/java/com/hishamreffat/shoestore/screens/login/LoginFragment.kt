package com.hishamreffat.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.hishamreffat.shoestore.R
import com.hishamreffat.shoestore.database.users.UserDatabase
import com.hishamreffat.shoestore.databinding.FragmentLoginBinding
import com.hishamreffat.shoestore.models.shoes.ShoeViewModel
import com.hishamreffat.shoestore.models.user.UserViewModel
import com.hishamreffat.shoestore.models.user.UserViewModelFactory

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login,container,false)

        val application = requireNotNull(this.activity).application

        val dataSource = UserDatabase.getInstance(application).userDao

        val viewModelFactory = UserViewModelFactory(dataSource, application)

        val viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
        binding.loginAccount.setOnClickListener { view:View ->
            viewModel.insertUser()
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
        return  binding.root
    }

}