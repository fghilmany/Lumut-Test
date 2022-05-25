package com.fghilmany.mvvmstarterproject.ui.main

import androidx.lifecycle.*
import com.fghilmany.mvvmstarterproject.core.data.DataRepository
import timber.log.Timber

class MainViewModel(private val dataRepository: DataRepository): ViewModel() {

    var id = MutableLiveData<String>()

    init {
        id.value = "1"
    }

    fun setId(id: String){
        this.id.value = id
    }

    fun getTodos() =  dataRepository.getTodos().asLiveData()

    fun getDetailTodos() = dataRepository.getDetailTodos(id.value.toString()).asLiveData()
}