package com.example.newengland.UserPages.UserPage

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.newengland.databinding.ActivityProfilBinding
import com.example.newengland.main.MainApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

import org.wit.placemark.showImagePicker



class Profil_Activity : AppCompatActivity() {


    private lateinit var binding: ActivityProfilBinding

    lateinit var database: DatabaseReference
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var bildURI: Uri
    private lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MainApp
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Profil"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

        var uid = app.userID
        println(uid)
        database =
            FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference().child("User")


        readFirstName(database, uid)

        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()

        binding.EditButton.setOnClickListener()
        {
            val intent = Intent(this, EditUserData::class.java)
            startActivity(intent)
        }


        binding.imageView.setOnClickListener() {

            showImagePicker(imageIntentLauncher, this)

        }

        registerImagePickerCallback()

    }

    private fun readFirstName(database: DatabaseReference, uid: String) {

            database.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val firstName = dataSnapshot.child("firstName").getValue(String::class.java)
                    val lastName = dataSnapshot.child("lastName").getValue(String::class.java)
                    val email = dataSnapshot.child("email").getValue(String::class.java)
                    val favoriteClub = dataSnapshot.child("favoriteClub").getValue(String::class.java)



                    binding.editTextVorname.text= firstName
                    binding.editTextEmail.text = email
                    binding.editTextNachname.text = lastName
                    binding.editTextfavoriteClub.text = favoriteClub

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    println("Error reading firstName: " + databaseError.message)
                }
            })

    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {

                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(
                                image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                            )

                            bildURI = image

                            Picasso.get()
                                .load(bildURI)
                                .into(binding.imageView)

                        }
                    }
                    AppCompatActivity.RESULT_CANCELED -> {}
                    else -> {}
                }
            }
    }

    private fun getData() {

  /*      database =
            FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference().child("User")
        var uid = app.userID
        println(uid)
        database.child("fmIImrVGJFcKArn4OwQQJ8MPuKY2").get().addOnSuccessListener {


            val firstName = database.child("firstName").get
            println(firstName)
                    val vorName= it.child("vorName").value.toString()
                    /*val firstName = snapshot.child("firstName").getValue(String::class.java)
                    val lastName = snapshot.child("lastName").getValue(String::class.java)
                    val favoriteClub = snapshot.child("favoriteClub").getValue(String::class.java)
                    val email = snapshot.child("email").getValue(String::class.java)*/

                //    binding.editTextEmail.text = email
                    binding.editTextVorname.text = firstName as CharSequence?
               //     binding.editTextNachname.text = lastName
                 //   binding.editTextfavoriteClub.text = favoriteClub

                }*/








    }
}






