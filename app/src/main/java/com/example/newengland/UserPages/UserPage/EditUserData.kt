package com.example.newengland.UserPages.UserPage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.Model.UserModel

import com.example.newengland.databinding.ActivityEditUserDataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditUserData : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserDataBinding
    private lateinit var firebaseAuth: FirebaseAuth



    lateinit var database: DatabaseReference
    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.newengland.R.layout.activity_edit_user_data)

        binding = ActivityEditUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.UpdateDataButton.setOnClickListener()
        {

            val email= binding.editTextEmail.text.toString()
            val firstName = binding.editTextNachname.text.toString()
            val lastName =binding.editTextNachname.text.toString()
            val favoriteClub =binding.editTextfavoriteClub.text.toString()
            database =
                FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference()

            val androidId: String =
                Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

            database.child("User").child(androidId)
                .setValue(UserModel(firstName, lastName, email, favoriteClub))

            val intent = Intent(this,   Profil_Activity::class.java)
            startActivity(intent)

        }
    }
}
