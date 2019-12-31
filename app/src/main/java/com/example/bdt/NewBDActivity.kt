package com.example.bdt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class NewBDActivity : AppCompatActivity() {
    private lateinit var editBDName: EditText
    private lateinit var editBDDOB: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_bd)
        editBDName = findViewById(R.id.edit_name)
        editBDDOB = findViewById(R.id.edit_dob)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editBDName.text) || TextUtils.isEmpty(editBDDOB.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editBDName.text.toString()
                val dob = editBDDOB.text.toString().toLong()

                replyIntent.putExtra(EXTRA_NAME, name)
                replyIntent.putExtra(EXTRA_DOB, dob)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.bdlistsql.REPLY"
        const val EXTRA_NAME = "com.example.android.bdlistsql.NAME"
        const val EXTRA_DOB = "com.example.android.bdlistsql.DOB"
    }
}
