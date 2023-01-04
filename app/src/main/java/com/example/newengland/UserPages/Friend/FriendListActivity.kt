package com.example.newengland.UserPages.Friend


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newengland.Model.FriendModel
import com.example.newengland.Model.UserModel
import com.example.newengland.R

import com.example.newengland.UserPages.UserPage.UserStart
import com.example.newengland.adapters.FriendAdapter


import com.example.newengland.adapters.friendListener

import com.example.newengland.databinding.ActivityFriendListBinding
import com.example.newengland.databinding.CardPlacemarkBinding
import com.example.newengland.main.MainApp
import com.google.firebase.database.*


class FriendListActivity : AppCompatActivity(), friendListener {
    lateinit var app: MainApp
    private lateinit var binding: ActivityFriendListBinding
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFriendListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        app = application as MainApp
        val layoutManager = LinearLayoutManager(this)


        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = FriendAdapter(app.usermodels.findAll(),this)


        val actionBar = supportActionBar
        if(actionBar != null)
        {
            actionBar.title="Friendslist"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, FriendsList::class.java)
                getResult.launch(launcherIntent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.usermodels.findAll().size)

            }
        }
    override fun onPlacemarkClick(friend: FriendModel, pos : Int) {
        val launcherIntent = Intent(this, FriendsList::class.java)
        launcherIntent.putExtra("friend_edit", friend)
        position = pos
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.usermodels.findAll().size)
            }
            else // Deleting
                if (it.resultCode == 99)
                    (binding.recyclerView.adapter)?.notifyItemRemoved(position)
        }




}




