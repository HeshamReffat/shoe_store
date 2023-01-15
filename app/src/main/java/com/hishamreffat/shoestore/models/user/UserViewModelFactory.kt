package com.hishamreffat.shoestore.models.user

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hishamreffat.shoestore.database.users.UserDatabaseDao

class UserViewModelFactory(
    private val databaseDao: UserDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return  UserViewModel(databaseDao,application) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model Class")
    }
}