package com.example.newengland.UserPages.UserPage
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.SignInOut.SignInActivity
import com.example.newengland.databinding.ActivityForumBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignOut : AppCompatActivity() {
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



            }
        }




