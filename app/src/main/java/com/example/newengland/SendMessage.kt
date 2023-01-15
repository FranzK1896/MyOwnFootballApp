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


class SendMessage : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var email: String
    private lateinit var binding: ActivitySendMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySendMessageBinding.inflate(layoutInflater)
setContentView(binding.root)
        title = "Ticketstore"


        val spinner: Spinner = findViewById(R.id.spinner2)

        ArrayAdapter.createFromResource(
            this,
            R.array.Clubs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
            val spinner: Spinner = findViewById(R.id.spinner2)
            spinner.onItemSelectedListener = this

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    if(selectedItem== "Miami Dolphins")
                    {
                        email= "finstix@dolphins.com";
                    }
                    else if(selectedItem == "New England Patriots")
                    {
                        email = "tickets@patriots.com"
                    }
                    else if(selectedItem == "Buffalo Bills")
                    {
                        email = "tickets@buffalobills.com"
                    }
                    else if(selectedItem == "New York Jets")
                    {
                        email = "tickets@newyorkjets.com"
                    }
                    else if(selectedItem == "New York Giants")
                    {
                        email == "nyticket@giants.nfl.net"
                    }
                    else if(selectedItem == "Minnesota Vikings")
                    {
                        email = "customerservice@vikings.nfl.com"
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            binding.Email.setOnClickListener()
            {
                sendHtmlEmail(email)
            }

        }


    }






    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun sendHtmlEmail(email: String) {
        val mailId = email
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mailId, null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject text here")
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p><b>Some Content</b></p>" +
                "http://www.google.com" + "<small><p>More content</p></small>"))
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }
}



