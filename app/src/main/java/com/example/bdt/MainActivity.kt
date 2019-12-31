package com.example.bdt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var bdViewModel: BDViewModel
    private val newBDActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bdViewModel = ViewModelProvider(this).get(BDViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BDAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        bdViewModel.allBDs.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setBDS(it) }
        })

        fab.setOnClickListener {
            val intent = Intent(this, NewBDActivity::class.java)
            startActivityForResult(intent, newBDActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newBDActivityRequestCode){
            if (resultCode == Activity.RESULT_OK) {
                val _name = data?.getStringExtra(NewBDActivity.EXTRA_NAME)
                val _dob = data?.getLongExtra(NewBDActivity.EXTRA_DOB, 0)

                val bd = BD(id = 0, name = _name!!, dob = _dob!!)
                bdViewModel.insertBD(bd)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                    "Data not saved because it is empty.",
                    Toast.LENGTH_LONG).show()
        }

    }
}
