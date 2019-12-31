package com.example.bdt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BDViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BDRepository

    val allBDs: LiveData<List<BD>>

    init {
        val bdDao = BDDatabase.getInstance(application,viewModelScope).bdDao()
        repository = BDRepository(bdDao)
        allBDs = repository.allBD
    }

    fun insertBD(bd: BD) = viewModelScope.launch {
        repository.insertBD(bd)
    }
}