package uz.salikhdev.ecology.core.app

import androidx.appcompat.app.AppCompatDelegate
import com.prongbang.localization.LocalizationApplication

class App : LocalizationApplication() {


    companion object {
        var context: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        context = this
    }


}