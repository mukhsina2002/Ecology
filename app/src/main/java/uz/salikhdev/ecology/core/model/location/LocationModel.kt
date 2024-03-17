package uz.salikhdev.ecology.core.model.location

import com.google.android.gms.maps.model.LatLng

data class LocationModel(
    val id: Int,
    val title: String,
    val latLon: LatLng,
    val image: String,
    val type: Int,
    val about: String,
    val phoneNumber:String
)