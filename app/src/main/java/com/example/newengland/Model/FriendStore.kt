package com.example.newengland.Model

interface FriendStore {
    fun findAll(): List<FriendModel>
    fun create(friend: FriendModel)
    fun update(friend: FriendModel)
    fun delete(friend: FriendModel)
}