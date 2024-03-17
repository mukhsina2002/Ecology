package uz.salikhdev.ecology.ui.plant_detail

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.base.BaseFragment
import uz.salikhdev.ecology.core.cache.LocalStorage
import uz.salikhdev.ecology.databinding.ScreenPlantDetailBinding
import uz.salikhdev.ecology.ui.plant.PlantViewModel

class PlantDetailScreen : BaseFragment(R.layout.screen_plant_detail) {

    private val binding by viewBinding(ScreenPlantDetailBinding::bind)
    private val args: PlantDetailScreenArgs by navArgs()
    private val viewModel: PlantViewModel by viewModels()
    private lateinit var link: String
    private var localStorage: LocalStorage? = null


    override fun onViewCreated() {
        localStorage = LocalStorage(requireContext())

        viewModel.getPlantDetail(args.id, localStorage!!.language)
        loadAction()
        observer()


    }


    private fun observer() {
        viewModel.plantDetailLD.observe(viewLifecycleOwner) {

            binding.backImage.load("https://ecology.salikhdev.uz${it.backgroundImage}")
            binding.littleImage.load("https://ecology.salikhdev.uz${it.image}")
            binding.title.text = it.title
            binding.discription.text = it.description
            binding.type.text = it.type
            this.link = it.link

        }
    }

    private fun loadAction() {

        binding.linkButton.setOnClickListener {
            val uri = Uri.parse(link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


    }


}