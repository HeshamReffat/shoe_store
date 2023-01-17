package com.hishamreffat.shoestore.models.shoes

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hishamreffat.shoestore.database.shoes.ShoeItem
import com.hishamreffat.shoestore.database.shoes.ShoeItemDatabaseDao
import kotlinx.coroutines.launch

class ShoeViewModel(val database: ShoeItemDatabaseDao, application: Application) :
    AndroidViewModel(application) {
    //data class ShoeItem(val name: String)
    var shoeName = MutableLiveData<String>()
    var shoeCompany = MutableLiveData<String>()
    var shoeSize = MutableLiveData<Int>()
    var shoeDescription = MutableLiveData<String>()
    private var _shoeList = MutableLiveData<ShoeItem>()
    val shoes: LiveData<List<ShoeItem?>> = database.getShoes()
    val shoeItemName = Transformations.map(shoes) { shoes ->
        parseShoeName(shoes)
    }

    private fun parseShoeName(shoe: List<ShoeItem?>): String {
        var name: String? = null
        // Log.i("ShowListViewModel", "ShoesitemList ${shoe}")
        shoe.forEach {
            if (it != null) {
                name = it.shoeName
                //return  name!!
            }
        }
        return name ?: "notfound"
    }

    init {
       // insertShoe()
        shoeName.value = ""
        shoeCompany.value = ""
        shoeDescription.value = ""
        shoeSize.value = 0
        Log.i("ShowListViewModel", "ShoesViewModel Created")
    }

    private suspend fun getShoeFromDatabase(): ShoeItem? {
        val shoe = database.getShoe()
        return shoe
    }

    fun insertShoe() {
        viewModelScope.launch {
            var shoe = ShoeItem()
            shoe.shoeName = shoeName.value?: "shoe"
            shoe.shoeCompany = shoeCompany.value?: "hesham"
            shoe.shoeSize = shoeSize.value?: 38
            shoe.shoeDescription = shoeDescription.value?: "no description"
            insert(shoe)
            _shoeList.value = getShoeFromDatabase()
            shoeName.value = ""
            shoeCompany.value = ""
            shoeDescription.value = ""
            shoeSize.value = 0
            Log.i("sss", "Shoe Inserted ${_shoeList.value}")
        }
    }

    private suspend fun insert(shoe: ShoeItem) {
        database.insert(shoe)
    }
}