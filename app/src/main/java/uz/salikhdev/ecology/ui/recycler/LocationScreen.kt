package uz.salikhdev.ecology.ui.recycler

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.base.BaseFragment
import uz.salikhdev.ecology.core.model.location.LocationModel
import uz.salikhdev.ecology.core.util.getLocationData
import uz.salikhdev.ecology.databinding.ScreenLocationBinding
import uz.salikhdev.ecology.ui.dialog.OpenGoogleMap
import uz.salikhdev.ecology.ui.dialog.RecyclerDialog


class LocationScreen : BaseFragment(R.layout.screen_location) {

    private val binding by viewBinding(ScreenLocationBinding::bind)
    private var myMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("PotentialBehaviorOverride")
    override fun onViewCreated() {

        if (!checkLocationPermission()) {
            requestLocationPermission()
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapView) as? SupportMapFragment
        mapFragment?.getMapAsync {
            myMap = it

            for (i in getLocationData()) {
                when (i.type) {
                    1 -> {
                        val marker = MarkerOptions()
                            .title(i.title)
                            .position(i.latLon)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_metal))
                        it.addMarker(marker)
                    }

                    2 -> {
                        val marker = MarkerOptions()
                            .title(i.title)
                            .position(i.latLon)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plastic))
                        it.addMarker(marker)
                    }

                    else -> {
                        val marker = MarkerOptions()
                            .title(i.title)
                            .position(i.latLon)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_paper))
                        it.addMarker(marker)
                    }
                }
            }

            myMap?.setOnMarkerClickListener(GoogleMap.OnMarkerClickListener { marker ->

                lateinit var model: LocationModel
                for (i in getLocationData()) {
                    if (i.title == marker.title) {
                        model = i
                        break
                    }
                }

                val dialog = RecyclerDialog(requireContext(), model)
                dialog.serGoogleClick(object : OpenGoogleMap {
                    override fun openGoogleNavigator(clinicsLocationModel: LocationModel) {
                        goToGoogleMap(clinicsLocationModel)
                    }
                })
                dialog.show()


                return@OnMarkerClickListener true
            })

        }
        getLastLocation()

        binding.findLocation.setOnClickListener {
            getLastLocation()
        }

    }

    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    val marker = MarkerOptions()
                        .title("Me")
                        .position(LatLng(latitude, longitude))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_person_location))

                    myMap?.let {
                        it.addMarker(marker)
                        it.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    latitude,
                                    longitude
                                ), 13.0f
                            )
                        )
                    }
                    Log.d("TAGaaaaaa", "${latitude} : ${location}")

                } else {
                    //TODO kemadi ............
                    Log.d("TAGaaaaaa", "Kmadi")
                }
            }
            .addOnFailureListener { e ->
                Log.d("TAGaaaaaa", "Hatolik")
            }
    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                requestLocationPermission()
            }
        }
    }


    private fun goToGoogleMap(m: LocationModel) {

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("google.navigation:q=${m.latLon.latitude},${m.latLon.longitude}&mode=d")
        )
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }

    }


}