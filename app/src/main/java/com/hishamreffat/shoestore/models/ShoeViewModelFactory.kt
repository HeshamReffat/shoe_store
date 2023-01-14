package com.hishamreffat.shoestore.models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hishamreffat.shoestore.database.ShoeItemDatabaseDao

@Suppress("UNCHECKED_CAST")
class ShoeViewModelFactory(private val dataSource: ShoeItemDatabaseDao, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ShoeViewModel::class.java)){
            return ShoeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}