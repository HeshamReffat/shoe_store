package com.hishamreffat.shoestore.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoesListViewModel : ViewModel() {
    data class ShoeItem(val name: String)

    private var _shoeList = MutableLiveData<MutableList<ShoeItem>>()
  //  private lateinit var _itemList: MutableList<ShoeItem>

    val shoeList: LiveData<MutableList<ShoeItem>>
        get() = _shoeList

    init {
        _shoeList.value = mutableListOf()
        for (i in 1..30) {
           // _itemList.add(ShoeItem("Shoe Number $i"))
          //  Log.i("cccccccc", "List:$_itemList ")
          //  _shoeList.value?.add()

//                ShoeItem(name = "first"),
//                ShoeItem(name = "second"),
//                ShoeItem(name = "third"),
//                ShoeItem(name = "fourth"),
//                ShoeItem(name = "fifth"),
//                ShoeItem(name = "six"),
//                ShoeItem(name = "seven"),
//                ShoeItem(name = "eight"),
//                ShoeItem(name = "nein"),
//                ShoeItem(name = "ten"),
            _shoeList.value!!.add(ShoeItem("Shoe Number $i"))
        }

        Log.i("ShowListViewModel", "ShoesViewModel Created")
    }
}