package uz.salikhdev.ecology.core.cache

import android.content.Context
import com.securepreferences.SecurePreferences
import uz.salikhdev.ecology.core.util.BooleanPreference
import uz.salikhdev.ecology.core.util.StringPreference

class LocalStorage(context: Context) {


    private val KEY = "SDAFASFD24534T2EGFDSD3OI4JFEPOIJEF0394TGJPEOIWJ"
    private val securePref = SecurePreferences(context, KEY, "local_storage.xml")

    var notificationOn: Boolean by BooleanPreference(securePref, true)
    var language: String by StringPreference(securePref, "en")

}