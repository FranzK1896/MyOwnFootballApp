package com.example.newengland.Model

import android.content.Context
import android.net.Uri
import com.example.newengland.helpers.exists
import com.example.newengland.helpers.read
import com.example.newengland.helpers.write
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "friend.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<FriendModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FriendJSONStore(private val context: Context) : FriendStore {

    var friends = mutableListOf<FriendModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FriendModel> {
        logAll()
        return friends
    }

    override fun create(friend: FriendModel) {
        friend.id = generateRandomId()
        friends.add(friend)
        serialize()
    }


    override fun update(friend: FriendModel) {
        val friendsList = findAll() as ArrayList<FriendModel>
        var foundFriend: FriendModel? = friendsList.find { p -> p.id == friend.id }
        if (foundFriend != null) {
            foundFriend.firstName = friend.firstName
            foundFriend.email = friend.email
            foundFriend.favoriteClub = friend.favoriteClub

        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(friends, listType)
        write(context, JSON_FILE, jsonString)
    }
    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        friends = gsonBuilder.fromJson(jsonString, listType)
    }

    override fun delete(friend: FriendModel) {
        friends.remove(friend)
        serialize()
    }


    private fun logAll() {
        friends.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}