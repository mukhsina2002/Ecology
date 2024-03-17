package uz.salikhdev.ecology.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.base.BaseFragment


@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment(R.layout.screen_splash) {

    override fun onViewCreated() {

        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToHomeScreen())
        }

    }

}