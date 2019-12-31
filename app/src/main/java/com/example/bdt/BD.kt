package com.example.bdt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "birthday")
class BD (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "dob")
    val dob: Long
    )