package uz.salikhdev.ecology

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.prongbang.localization.LocalizationAppCompatActivity
import uz.salikhdev.ecology.core.cache.LocalStorage
import java.util.Locale

class MainActivity : LocalizationAppCompatActivity() {

    private var localStorage: LocalStorage? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setTitle(R.string.main_activity_title)


        localStorage = LocalStorage(this)

        if (localStorage?.language == "en") {
            setLocale(Locale("en"))
        } else {
            setLocale(Locale("ru"))
        }
        if (!checkLocationPermission()) {
            requestLocationPermission()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS,
                    ),
                    101
                )
            }

        }
        createNotificationChannel()


    }


    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
            101
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                ),
                101
            )
        }

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "forandroidRemainderChanell"
            val description = "Muxsina"
            val importence = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("forandroid", name, importence).apply {
                this.description = description
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)

        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        openPrepareLocalize()
        super.onConfigurationChanged(newConfig)
    }

}