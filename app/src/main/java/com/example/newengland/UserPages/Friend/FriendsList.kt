package com.example.newengland.UserPages.Friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.provider.SyncStateContract.Helpers.update
import android.view.Menu
import android.view.MenuItem
import com.example.newengland.Model.FriendModel
import com.example.newengland.Model.UserModel
import com.example.newengland.R
import com.example.newengland.databinding.ActivityFriendBinding
import com.example.newengland.main.MainApp
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import timber.log.Timber
import timber.log.Timber.i


class FriendsList : AppCompatActivity() {
    private lateinit var binding: ActivityFriendBinding
    var usermodel = FriendModel()
    lateinit var app: MainApp
    var edit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.setDisplayHomeAsUpEnabled(true)



        Timber.plant(Timber.DebugTree())
        app = application as MainApp


        if (intent.hasExtra("friend_edit")) {
            edit = true
            usermodel = intent.extras?.getParcelable("friend_edit")!!
            binding.editVorname.setText(usermodel.firstName)
            binding.description.setText(usermodel.email)
            binding.FootballClub.setText(usermodel.favoriteClub)
            binding.btnAdd.setText("Save Friend")
        }

        binding.btnAdd.setOnClickListener() {
            usermodel.firstName = binding.editVorname.text.toString()
            usermodel.email = binding.description.text.toString()
            usermodel.favoriteClub = binding.FootballClub.text.toString()



            if (usermodel.firstName.isEmpty()) {
                Snackbar.make(it, R.string.enter_friendfirstName_title, Snackbar.LENGTH_LONG).show()
            } else {
                if (edit) {
                    app.usermodels.update(usermodel.copy())

                } else {
                    app.usermodels.create(usermodel.copy())
                }
            }

            setResult(RESULT_OK)
            finish()
        }

    }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_placemark, menu)
            if (edit) menu.getItem(0).isVisible = true
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.item_delete -> {
                    setResult(99)
                    app.usermodels.delete(usermodel)
                    finish()
                }
                R.id.item_cancel -> {
                    finish()
                }
            }
            return super.onOptionsItemSelected(item)
        }

}




