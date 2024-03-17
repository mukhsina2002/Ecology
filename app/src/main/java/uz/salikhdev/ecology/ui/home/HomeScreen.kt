package uz.salikhdev.ecology.ui.home

import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.prongbang.localization.LocalizationAppCompatActivity
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.base.BaseFragment
import uz.salikhdev.ecology.core.cache.LocalStorage
import uz.salikhdev.ecology.databinding.ScreenHomeBinding
import java.util.Locale

class HomeScreen : BaseFragment(R.layout.screen_home) {

    private val binding by viewBinding(ScreenHomeBinding::bind)
    private var localStorage: LocalStorage? = null

    override fun onViewCreated() {

        localStorage = LocalStorage(requireContext())

        binding.languageView.setOnClickListener {

            if (localStorage?.language == "en") {
                localStorage?.language = "ru"
                binding.languageView.setImageResource(R.drawable.russia_flag)
                (activity as LocalizationAppCompatActivity).setLocale(Locale("ru"))
            } else {
                localStorage?.language = "en"
                binding.languageView.setImageResource(R.drawable.usa_flag)
                (activity as LocalizationAppCompatActivity).setLocale(Locale("en"))
            }


        }

        if (localStorage?.language == "en") {
            binding.languageView.setImageResource(R.drawable.usa_flag)
        } else {
            binding.languageView.setImageResource(R.drawable.russia_flag)
        }



        binding.animals.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToAnimalScreen())
        }
        binding.planets.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToPlantScreen())
        }
        binding.utilize.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToLocationScreen())
        }
        binding.shoping.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionHomeScreenToNatureScreen())
        }


    }

}