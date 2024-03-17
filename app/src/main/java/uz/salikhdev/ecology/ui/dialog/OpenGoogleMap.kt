package uz.salikhdev.ecology.ui.dialog

import uz.salikhdev.ecology.core.model.location.LocationModel


interface OpenGoogleMap {
    fun openGoogleNavigator(clinicsLocationModel: LocationModel)
}