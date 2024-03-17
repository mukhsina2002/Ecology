package uz.salikhdev.ecology.core.util

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.gms.maps.model.LatLng
import uz.salikhdev.ecology.core.model.location.LocationModel

fun View.gone() {
    this.isGone = true
}


fun View.visible() {
    this.isVisible = true
}

fun getLocationData(): ArrayList<LocationModel> {

    val data = ArrayList<LocationModel>()

    data.add(LocationModel(1, "Uz-prista recycling", LatLng(41.329826, 69.276587), "https://avatars.mds.yandex.net/get-altay/1868686/2a0000016a35ac982fcf6caed7bef6de0a96/XXXL", 1, "", "+998 95 142 40 10"))
    data.add(LocationModel(2, "Tashbum", LatLng(41.353024, 69.261762), "https://i1.photo.2gis.com/images/branch/0/30258560096031493_531b_328x170.jpg", 1, "", "+998 99 977 38 59"))
    data.add(LocationModel(3, "We use", LatLng(41.311987, 69.290485), "https://i0.photo.2gis.com/images/branch/0/30258560097781865_b686_328x170.jpg", 1, "", "+998 90 356 78 60"))
    data.add(LocationModel(4, "Sarvar Bek med tex servis", LatLng(41.27417, 69.196459), "https://i9.photo.2gis.com/images/branch/0/30258560096970393_02e5_328x170.jpg", 1, "", "+998 95 144 67 70"))
    data.add(LocationModel(5, "Rosvtorsiryo ", LatLng(41.293511, 69.223845), "", 2, "", "+998 99 010 13 63"))
    data.add(LocationModel(6, "Sanoat Komunnal Servis ", LatLng(41.3189, 69.356716), "https://i7.photo.2gis.com/images/branch/0/30258560165395154_1866_328x170.jpg", 3, "", "No contact"))
    data.add(LocationModel(7, "O'zvtortcvetmet", LatLng(41.353459, 69.349515), "", 3, "", "+998 93 535 07 08"))
    data.add(LocationModel(8, "Eco-Recycling", LatLng(41.285967, 69.228089), "https://i4.photo.2gis.com/images/branch/0/30258560130773396_ff53_328x170.jpg", 3, "", "+998 97 139 49 10"))

    return data
}