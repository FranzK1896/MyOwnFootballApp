package com.example.newengland.UserPages.UserPage

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.databinding.ActivityProfilBinding
import com.google.firebase.database.*


class Profil_Activity : AppCompatActivity() {


    private lateinit var binding: ActivityProfilBinding

    lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        if(actionBar != null)
        {
            actionBar.title="Profil"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val androidId: String =
            Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        database =
            FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference().child("User").child(androidId)



        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        binding.EditButton.setOnClickListener()
        {
            val intent = Intent(this, EditUserData::class.java)
            startActivity(intent)
        }

    }


    private fun getData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("oooo", "onDataChange: $snapshot")

                val firstName= snapshot.child("firstName").getValue(String::class.java)
                val lastName= snapshot.child("lastName").getValue(String::class.java)
                val favoriteClub= snapshot.child("favoriteClub").getValue(String::class.java)
                val email= snapshot.child("email").getValue(String::class.java)

                binding.editTextEmail.text = email
                binding.editTextVorname.text = firstName
                binding.editTextNachname.text = lastName
                binding.editTextfavoriteClub.text = favoriteClub

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}






