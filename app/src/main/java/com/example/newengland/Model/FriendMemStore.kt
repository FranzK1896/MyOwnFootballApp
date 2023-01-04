package com.example.newengland.Model

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
class FriendMemStore : FriendStore {

    val friends = ArrayList<FriendModel>()

    override fun findAll(): List<FriendModel> {
        return friends
    }

    override fun create(friend: FriendModel) {
        friend.id= getId()
        friends.add(friend)
        logAll()
    }
    override fun update(friend: FriendModel) {
        var foundPlacemark: FriendModel? = friends.find { p -> p.id == friend.id }
        if (foundPlacemark != null) {
            foundPlacemark.firstName = friend.firstName
            foundPlacemark.email = friend.email
            foundPlacemark.favoriteClub=friend.favoriteClub
            logAll()
        }
        else
        {
            println("Fehler")
        }
    }


    fun logAll() {
        friends.forEach{ i("${it}") }
    }
    override fun delete(friend: FriendModel) {
        friends.remove(friend)
    }
}