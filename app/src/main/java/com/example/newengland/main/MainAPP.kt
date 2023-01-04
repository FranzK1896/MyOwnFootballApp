package com.example.newengland.main

import android.app.Application
import com.example.newengland.Model.PlacemarkJSONStore
import com.example.newengland.Model.FriendStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {
    lateinit var usermodels: FriendStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        usermodels = PlacemarkJSONStore(applicationContext)


    }
}