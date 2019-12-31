package com.example.bdt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KParameter

@Database (entities = [BD::class],version = 1 )
abstract class BDDatabase:RoomDatabase() {

    abstract fun bdDao():BDDao

    private class BDDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.bdDao())
                }
            }
        }

        suspend fun populateDatabase(bdDao: BDDao) {
            // Delete all content here.
            bdDao.deleteAllBD()

            // Add sample
            var bd = BD(1,"Tan Chi Thing",10121999)
            bdDao.insertBD(bd)
            bd = BD(2,"Sze Yen Ying",24121999)
            bdDao.insertBD(bd)
            bd = BD(3,"Yoon Bo Ra",30121998)
            bdDao.insertBD(bd)

            // TODO: Add your own words!
        }
    }

    companion object{
        @Volatile
        private var INSTANCE:BDDatabase?=null
        fun getInstance(context:Context, scope: CoroutineScope):BDDatabase{
            synchronized(this){
                var instance= INSTANCE
                if(instance == null){
                    instance= Room.databaseBuilder( context.applicationContext,BDDatabase::class.java,"birthday_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(BDDatabaseCallback(scope))
                        .build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}

