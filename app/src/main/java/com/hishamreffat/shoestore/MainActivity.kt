package com.hishamreffat.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.hishamreffat.shoestore.database.shoes.ShoeItemDatabase
import com.hishamreffat.shoestore.database.users.UserDatabase
import com.hishamreffat.shoestore.models.shoes.ShoeViewModel
import com.hishamreffat.shoestore.models.shoes.ShoeViewModelFactory
import com.hishamreffat.shoestore.models.user.UserViewModel
import com.hishamreffat.shoestore.models.user.UserViewModelFactory
import com.hishamreffat.shoestore.screens.login.LoginFragmentDirections

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = requireNotNull(this).application

        val dataSource = UserDatabase.getInstance(application).userDao

        val viewModelFactory = UserViewModelFactory(dataSource, application)
        setContentView(R.layout.activity_main)
        val navigation = this.findNavController(R.id.loginFragment)
        NavigationUI.setupActionBarWithNavController(this, navigation)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]
        viewModel.user.observe(this, Observer { user ->
            if (user != null) {
                navigation.navigate(LoginFragmentDirections.actionLoginFragmentToShoesListFragment())
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        viewModel.user.observe(this, Observer { user ->
            if (user != null) {
                val navigation = this.findNavController(R.id.loginFragment)
                navigation.navigateUp()
            } else {
                val navigation = this.findNavController(R.id.shoesListFragment)
                navigation.navigateUp()
            }
        })
        return super.onNavigateUp()
    }
}