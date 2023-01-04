package com.example.newengland.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FriendModel(
    var id: Long =0,
    var firstName: String = "", val lastName:String="", var email:String="", var favoriteClub: String =""
): Parcelable