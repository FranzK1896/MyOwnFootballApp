package com.example.newengland.SignInOut

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.R
import com.example.newengland.Model.UserModel
import com.example.newengland.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth


    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
 //Initialsieren Firebase Auth
    firebaseAuth =FirebaseAuth.getInstance()




    binding.buttonRegisterUser.setOnClickListener()
    {

        val email= binding.editTextEmail.text.toString()
        val pass= binding.editPasswort.text.toString()
        val firstName = binding.editVorname.text.toString()
        val lastName =binding.editNachname.text.toString()
        val confirmPasswort = binding.editConfirmPasswort.text.toString()
        val favoriteClub =binding.editFavoriteClub.text.toString()


        database =
            FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference()

         val androidId: String =
        Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)


        database.child("User").child(androidId)
            .setValue(UserModel( firstName, lastName, email, favoriteClub))

        if(email.isNotEmpty() &&pass.isNotEmpty()&& confirmPasswort.isNotEmpty())
        {
            if(pass== confirmPasswort)
            {
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener()
                {
                if(it.isSuccessful)
                {
                    Toast.makeText(this, "Datenübertragen was sucessfull", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }else
                {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }

                }

            }else
            {
                Toast.makeText(this, "Passwort is not matching", Toast.LENGTH_SHORT).show()
            }
        }else
            Toast.makeText(this, "No Empty Fields are allowed", Toast.LENGTH_SHORT).show()
    }
    }
}