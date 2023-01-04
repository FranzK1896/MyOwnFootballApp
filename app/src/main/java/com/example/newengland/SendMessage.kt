package com.example.newengland

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View

class SendMessage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        title = "KotlinApp"
    }
    fun sendHtmlEmail(view: View?) {
        val mailId = "yourmail@gmail.com"
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mailId, null))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject text here")
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p><b>Some Content</b></p>" +
                "http://www.google.com" + "<small><p>More content</p></small>"))
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }
}
