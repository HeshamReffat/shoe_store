package com.hishamreffat.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = this.findNavController(R.id.loginFragment)
        NavigationUI.setupActionBarWithNavController(this, navigation)
    }

    override fun onSupportNavigateUp(): Boolean {

        val navigation = this.findNavController(R.id.loginFragment)
        navigation.navigateUp()
        return super.onNavigateUp()
    }
}