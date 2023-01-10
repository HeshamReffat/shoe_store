package com.hishamreffat.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoesListViewModel : ViewModel() {
    data class ShoeItem(val name: String)

    private var _shoeList = MutableLiveData<MutableList<ShoeItem>>()
    val shoeList: LiveData<MutableList<ShoeItem>>
        get() = _shoeList

    init {
        _shoeList.value = mutableListOf(ShoeItem(name = "first"))
        Log.i("ShowListViewModel", "ShoesViewModel Created")
    }
}