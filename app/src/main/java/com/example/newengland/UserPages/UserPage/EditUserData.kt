package com.example.newengland.UserPages.UserPage

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.Model.UserModel

import com.example.newengland.databinding.ActivityEditUserDataBinding
import com.example.newengland.main.MainApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import org.wit.placemark.showImagePicker

class EditUserData : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserDataBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var bildURI : Uri

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.newengland.R.layout.activity_edit_user_data)

        binding = ActivityEditUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        actionBar?.setDisplayHomeAsUpEnabled(true)
        app = application as MainApp
        var uid = app.userID
        binding.UpdateDataButton.setOnClickListener()
        {

            val email= binding.editTextEmail.text.toString()
            val firstName = binding.editTextVorname.text.toString()
            val lastName =binding.editTextNachname.text.toString()
            val favoriteClub =binding.editTextfavoriteClub.text.toString()
            database =
                FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference()



            database.child("User").child(uid)
                .setValue(UserModel(firstName, lastName, email, favoriteClub))

            val intent = Intent(this,   Profil_Activity::class.java)
            startActivity(intent)

        }

        binding.imageView.setOnClickListener(){

            showImagePicker(imageIntentLauncher, this)

        }
        registerImagePickerCallback()
    }
    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    AppCompatActivity.RESULT_OK -> {
                        if (result.data != null) {

                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)

                            bildURI = image

                            Picasso.get()
                                .load(bildURI)
                                .into(binding.imageView)

                        }
                    }
                    AppCompatActivity.RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}
