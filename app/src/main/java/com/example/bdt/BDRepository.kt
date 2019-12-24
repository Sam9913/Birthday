package com.example.bdt

import android.app.Application
import androidx.lifecycle.LiveData

class BDRepository(private val bdDao: BDDao) {

    val allBD: LiveData<List<BD>> = bdDao.getAllBD()

    suspend fun insertBD(bd: BD) {
        bdDao.insertBD(bd)
    }
}