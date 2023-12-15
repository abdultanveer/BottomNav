package com.example.bottomnav.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.data.Item
import com.example.bottomnav.data.database.ItemDao
import kotlinx.coroutines.launch

//insert into item(id,itemName,itemPrice,quantityInStock)values(22,"groceries",33.00,33)
class DashboardViewModel(private val itemDao: ItemDao) : ViewModel() {
    fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()

    }

    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()


    private val _text = MutableLiveData<String>().apply {
        value = "This data  is being served by dashboard view model"
    }
    val text: LiveData<String> = _text
}


class InventoryViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")    }
}