package com.example.newengland.UserPages.UserPage
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.R
import com.example.newengland.SignInOut.SignInActivity
import com.example.newengland.databinding.ActivityEditUserDataBinding
import com.example.newengland.databinding.ActivityForumBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.Calendar



class Forum : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var binding: ActivityForumBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityForumBinding.inflate(layoutInflater)
            setContentView(binding.root)
            val actionBar = supportActionBar
            if(actionBar != null)
            {
                actionBar.title="Ausloggen"
            }
            actionBar?.setDisplayHomeAsUpEnabled(true)


            auth = Firebase.auth
            Firebase.auth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            }
        }




