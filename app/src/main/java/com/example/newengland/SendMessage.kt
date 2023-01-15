package com.example.newengland

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.databinding.ActivitySendMessageBinding


class SendMessage : AppCompatActivity() {

    lateinit var email: String
    private lateinit var binding: ActivitySendMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySendMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Ticketstore"


    }
}




