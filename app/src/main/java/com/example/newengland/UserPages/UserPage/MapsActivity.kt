package com.example.newengland.UserPages.UserPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.newengland.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.newengland.databinding.ActivityMapsBinding
import com.example.newengland.main.MainApp
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var markerList = mutableListOf<Marker>()
    lateinit var app: MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        if(actionBar != null)
        {
            actionBar.title="NFl Stadion Places"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)


        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app = application as MainApp
        this.markerList = app.markerList

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera

        val NewEnglandPatriots = LatLng( 42.1037066655472, -71.26731518260875)
        mMap.addMarker(MarkerOptions().position(NewEnglandPatriots).title("Marker at New England Patriots"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(NewEnglandPatriots))

        val BuffaloBills = LatLng(  42.81560980695675, -78.80567507759218)
        mMap.addMarker(MarkerOptions().position(BuffaloBills).title("Marker at Buffalo Bills"))

        val MiamiDolphins = LatLng( 25.95808218840841, -80.23893552340733)
        mMap.addMarker(MarkerOptions().position(MiamiDolphins).title("Marker at  Miami Dolphins"))

        val NewYorkGiants = LatLng( 40.813725619576786, -74.07441408680421)
        mMap.addMarker(MarkerOptions().position(NewYorkGiants).title("Marker at New New York Jets/Giants"))

        val Vikings = LatLng(   44.97376751770971, -93.25750523084614)
        mMap.addMarker(MarkerOptions().position(Vikings).title("Marker at Minneasota Vikings"))

        mMap.setOnMapLongClickListener { latLng ->

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Football Place!")
            val input = EditText(this)
            builder.setView(input)
            builder.setPositiveButton("Speichern") { dialog, which ->
                val markerName = input.text.toString()

                val marker = mMap.addMarker(MarkerOptions().position(latLng).title(markerName))
                if (marker != null) {
                    markerList.add(marker)
                };

            }
            builder.setNegativeButton("Abbrechen") { dialog, which ->
                dialog.cancel()
            }
            builder.show()
        }
    }
}