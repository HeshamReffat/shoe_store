package com.hishamreffat.shoestore.models.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hishamreffat.shoestore.database.users.User
import com.hishamreffat.shoestore.database.users.UserDatabaseDao
import kotlinx.coroutines.launch

class UserViewModel(val databaseDao: UserDatabaseDao, application: Application) :
    AndroidViewModel(application) {
    val user = MutableLiveData<User?>()

    init {
        getOneUser()
        Log.i("sss", "User model Created")
    }

    fun getOneUser(){
        viewModelScope.launch {
            user.value = getUser()
        }
}
    fun insertUser(){
        viewModelScope.launch {
            val user = User()
            insertNewUser(user)
        }
    }
    fun userLogout(){
        viewModelScope.launch {
            clear()
        }
    }
    private suspend fun getUser(): User? {
        val user = databaseDao.getUser()
        Log.i("sss", user?.userId.toString())
        return user
    }
    private suspend fun insertNewUser(user:User){
        databaseDao.insert(user)
    }
    private suspend fun clear() {
        databaseDao.clear()
    }

}