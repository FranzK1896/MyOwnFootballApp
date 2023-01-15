package com.example.newengland.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newengland.Model.FriendModel
import com.example.newengland.databinding.CardPlacemarkBinding
import com.google.firebase.database.*

interface friendListener {
    fun onPlacemarkClick(friend: FriendModel, position : Int)
}

class FriendAdapter constructor(private var friends: List<FriendModel>,
                                   private val listener: friendListener ) :
    RecyclerView.Adapter<FriendAdapter.MainHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPlacemarkBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val friends = friends[holder.adapterPosition]
        holder.bind(friends, listener)
    }

    override fun getItemCount(): Int = friends.size


    class MainHolder(private val binding : CardPlacemarkBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(friend: FriendModel,  listener: friendListener) {

            binding.VornameTitle.text = friend.firstName
            binding.description.text = friend.email
            binding.favClub.text =friend.favoriteClub





                  /*  binding.placemarkTitle.text = firstName
                    binding.description.text = lastName*/
                    binding.root.setOnClickListener { listener.onPlacemarkClick(friend, adapterPosition) }


        }



        }
}