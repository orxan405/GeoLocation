package com.nexis.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.nexis.location.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var mMap: GoogleMap

    lateinit var mapFragment: SupportMapFragment
    lateinit var mMap: GoogleMap

    lateinit var binding: ActivityMainBinding

    private var izinKontrol = 0
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask: Task<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            mMap = it

            val location1 = LatLng(40.4117832, 49.825815)
            mMap.addMarker(MarkerOptions().position(location1).title("Marker in Xutor"))
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(location1))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1, 10f))
        })

        //flpc = LocationServices.getFusedLocationProviderClient(this)


//        binding.btnKonumAl.setOnClickListener {
//            izinKontrol =
//                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//
//            if (izinKontrol != PackageManager.PERMISSION_GRANTED) // icaze verilmeyibse
//            {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                    100
//                )
//            } else // icaze verilibse
//            {
//                locationTask = flpc.lastLocation
//                konumBilgisiAl()
//            }
//        }
    }

//      fun onMapready(googleMap: GoogleMap){
//        mMap = googleMap
//
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }


    fun konumBilgisiAl() {
        locationTask.addOnSuccessListener {
            if (it != null) {
//                binding.textViewEnlem.text = "Enlem : ${it.latitude}"
//                binding.textViewBoylam.text = "Boylam : ${it.longitude}"
            } else {
//                binding.textViewEnlem.text = "Enlem : alinmadi"
//                binding.textViewBoylam.text = "Boylam : alinmadi"
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 100) {
            izinKontrol =
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Icaze verildi...", Toast.LENGTH_SHORT).show()
                locationTask = flpc.lastLocation
                konumBilgisiAl()
            } else {
                Toast.makeText(applicationContext, "Icaze verilmedi...", Toast.LENGTH_SHORT).show()
            }
        }
    }

}