package com.example.newengland.UserPages.UserPage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.newengland.R
import com.example.newengland.databinding.ActivitySetEventBinding
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar
class SetEvent : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySetEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_event)
        binding = ActivitySetEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        if(actionBar != null)
        {
            actionBar.title ="Plan Events"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)


        val rootRef = FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference().child("Dates")

        database =
            FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference().child("Dates")


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("oooo", "onDataChange: $snapshot")







                val currentDate = Calendar.getInstance()

                val dateFormat = SimpleDateFormat("yyyy-MM-dd")

                val dateString = snapshot.child("Wildcard").getValue(String::class.java)
               val parsedDateWildcard = dateFormat.parse(dateString)
                val diffWC = parsedDateWildcard.time - currentDate.timeInMillis
                val diffDaysWildcard = diffWC / (24 * 60 * 60 * 1000)

                val Divisional= snapshot.child("Divisionalround").getValue(String::class.java)
                val parsedDateDivisional = dateFormat.parse(Divisional)
                val diffD = parsedDateDivisional.time - currentDate.timeInMillis
                val diffDaysDivisional = diffD / (24 * 60 * 60 * 1000)


                val Conference= snapshot.child("Conference").getValue(String::class.java)
                val parsedDateConference = dateFormat.parse(Conference)
                val diffCon = parsedDateConference.time - currentDate.timeInMillis
                val diffDaysCon = diffCon / (24 * 60 * 60 * 1000)

                val Superbowl= snapshot.child("Superbowl").getValue(String::class.java)
                val parsedDateSuperbowl = dateFormat.parse(Superbowl)
                val diffSb = parsedDateSuperbowl.time - currentDate.timeInMillis
                val diffDaysSB = diffSb/ (24 * 60 * 60 * 1000)

                binding.Wildcard.text = diffDaysWildcard .toString()
                binding.Dvivisonal.text=diffDaysDivisional.toString()
                binding.Conference.text=diffDaysCon.toString()
                binding.Superbowl.text=diffDaysSB.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })





        }
    }

private fun Calendar.setTime(dateFromFirebase1: String?) {

}

