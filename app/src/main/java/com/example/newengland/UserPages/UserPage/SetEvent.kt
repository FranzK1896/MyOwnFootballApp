package com.example.newengland.UserPages.UserPage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.R
import com.example.newengland.databinding.ActivitySetEventBinding
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar
class SetEvent : AppCompatActivity() {

    lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySetEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_event)
        binding = ActivitySetEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Plan Events"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)


    }
}
