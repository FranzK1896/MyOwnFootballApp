package com.example.newengland.UserPages.UserPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.newengland.R
import com.example.newengland.databinding.ActivityClubBinding
import com.example.newengland.databinding.ActivityEditUserDataBinding
import com.example.newengland.databinding.ActivitySendMessageBinding
import java.util.*

class Club : AppCompatActivity() {

    lateinit var Club: String
    lateinit var Club2: String
    private lateinit var binding: ActivityClubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "RamdomResults"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        binding = ActivityClubBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}