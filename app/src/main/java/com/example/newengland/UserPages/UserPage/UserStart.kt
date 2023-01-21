package com.example.newengland.UserPages.UserPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.newengland.R
import com.example.newengland.UserPages.Friend.FriendListActivity

import com.example.newengland.databinding.ActivityUserstartBinding
import com.google.firebase.database.*
import kotlin.random.Random

class UserStart : AppCompatActivity() {
    lateinit var binding: ActivityUserstartBinding
    lateinit var toggle: ActionBarDrawerToggle

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserstartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val randomNumber = generateRandomNumber(1, 4)
        database =
            FirebaseDatabase.getInstance("https://myfootballapp-f2338-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference().child("Facts").child(randomNumber.toString())


        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("oooo", "onDataChange: $snapshot")

                val Facts= snapshot.getValue(String::class.java)


                binding.editFacts.text = Facts


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })



        binding.apply {

        toggle= ActionBarDrawerToggle(this@UserStart, drawerlayoutID, R.string.open, R.string.close)
        drawerlayoutID.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navBar.setNavigationItemSelectedListener {
                when(it.itemId)
                {
                    R.id.ProfilItem->{
                        val intent = Intent(this@UserStart, Profil_Activity::class.java)
                        startActivity(intent)
                    }
                    R.id.ClubItem->
                    {
                        val intent = Intent(this@UserStart, Club::class.java)
                        startActivity(intent)
                    }
                    R.id.AusloggItem->
                    {
                        val intent = Intent(this@UserStart, SignOut::class.java)
                        startActivity(intent)
                    }
                    R.id.HighlightItem->
                    {
                        val intent = Intent(this@UserStart, SendMessage::class.java)
                        startActivity(intent)
                    }
                    R.id.FriendsList->
                    {
                        val intent = Intent(this@UserStart, FriendListActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.PlanEvents->
                    {
                        val intent = Intent(this@UserStart, SetEvent::class.java)
                        startActivity(intent)
                    }
                    R.id.map->
                    {
                        val intent = Intent(this@UserStart, MapsActivity::class.java)
                        startActivity(intent)
                    }

                }
                true


            }

        }
    }

    private fun generateRandomNumber(min: Int, max: Int): Int {
            val rand = Random

            return rand.nextInt(max - min + 1) + min
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {

            true
        }
        return super.onOptionsItemSelected(item)
    }
}
