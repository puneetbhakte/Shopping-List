package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.collections.List

class ListViewModel(app:Application,private val repository: ListRepository):AndroidViewModel(app) {

    fun addlist(list:com.example.shoppinglist.List){
        viewModelScope.launch {
            repository.upsertdata(list)
        }
    }
    fun deletelist(list: com.example.shoppinglist.List){
        viewModelScope.launch {
            repository.deletetdata(list)
        }
    }

    fun getalldata()= repository.getdata()


}