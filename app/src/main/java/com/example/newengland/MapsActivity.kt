package com.example.newengland

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.newengland.databinding.ActivityMapsBinding
import com.example.newengland.main.MainApp
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity() {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var markerList = mutableListOf<Marker>()
  lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "NFl Stadion Places"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)


        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MainApp

    }
}