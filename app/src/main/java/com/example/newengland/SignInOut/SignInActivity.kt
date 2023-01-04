package com.example.newengland.SignInOut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newengland.Model.UserModel
import com.example.newengland.UserPages.UserPage.UserStart
import com.example.newengland.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth =FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener()
        {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.LoginBtn.setOnClickListener()
        {
            val email= binding.editTextTextEmailAddress2.text.toString()
            val pass= binding.editTextTextPassword2.text.toString()

            if(email.isNotEmpty() &&pass.isNotEmpty())
            {

                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener()
                    {
                        if(it.isSuccessful)
                        {
                            val intent = Intent(this, UserStart::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Login was sucessfull", Toast.LENGTH_SHORT).show()
                        }else
                        {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }


            }else {
                Toast.makeText(this, "No Empty Fields are allowed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}