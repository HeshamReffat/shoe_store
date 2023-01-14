package com.hishamreffat.shoestore.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hishamreffat.shoestore.database.ShoeItem
import com.hishamreffat.shoestore.database.ShoeItemDatabaseDao
import kotlinx.coroutines.launch

class ShoeViewModel(val database: ShoeItemDatabaseDao, application: Application) :
    AndroidViewModel(application) {
    //data class ShoeItem(val name: String)

    private var _shoeList = MutableLiveData<ShoeItem>()
    private val shoes = database.getShoes()
    val shoeItemName = Transformations.map(shoes) { shoe ->
        parseShoeName(shoe)
    }

    private fun parseShoeName(shoe: List<ShoeItem?>): String {
        var name: String? = null
        shoe.forEach {
            if (it != null) {
                name = it.shoeName
            }
        }
        return name ?: "notfound"
    }

    init {
        insertShoe()
        Log.i("ShowListViewModel", "ShoesViewModel Created")

        Log.i("ShowListViewModel", "Shoesitem ${shoes.value}")

    }

    private suspend fun getShoeFromDatabase(): ShoeItem? {
        val shoe = database.getShoe()
        return shoe
    }

    fun insertShoe() {
        viewModelScope.launch {
            val shoe = ShoeItem()
            insert(shoe)
            _shoeList.value = getShoeFromDatabase()
            Log.i("sss", "Shoe Inserted ${_shoeList.value}")
        }
    }

    private suspend fun insert(shoe: ShoeItem) {
        database.insert(shoe)
    }
}