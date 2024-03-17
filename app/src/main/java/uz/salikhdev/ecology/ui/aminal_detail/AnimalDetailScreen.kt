package uz.salikhdev.ecology.ui.aminal_detail

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
import uz.salikhdev.ecology.databinding.ScreenAnimalDetailBinding
import uz.salikhdev.ecology.ui.animal.AnimalViewModel


class AnimalDetailScreen : BaseFragment(R.layout.screen_animal_detail) {

    private val binding by viewBinding(ScreenAnimalDetailBinding::bind)
    private val args: AnimalDetailScreenArgs by navArgs()
    private val viewModel: AnimalViewModel by viewModels()
    private var link: String = ""
    private var localStorage: LocalStorage? = null



    override fun onViewCreated() {
        localStorage = LocalStorage(requireContext())

        viewModel.getAnimalDetail(args.id , localStorage!!.language)
        observer()
        loadAction()

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

    private fun observer() {

        viewModel.animalDetailLD.observe(viewLifecycleOwner) {
            binding.backImage.load("https://ecology.salikhdev.uz${it.backgroundImage}")
            binding.littleImage.load("https://ecology.salikhdev.uz${it.image}")
            binding.title.text = it.name
            binding.discription.text = it.description
            binding.type.text = it.type
            this.link = it.link
        }


    }

}