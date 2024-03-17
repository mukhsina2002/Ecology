package uz.salikhdev.ecology.ui.animal

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.adapter.AnimalAdapter
import uz.salikhdev.ecology.core.base.BaseFragment
import uz.salikhdev.ecology.core.cache.LocalStorage
import uz.salikhdev.ecology.core.util.gone
import uz.salikhdev.ecology.core.util.visible
import uz.salikhdev.ecology.databinding.ScreenAnimalBinding

class AnimalScreen : BaseFragment(R.layout.screen_animal) {

    private val binding by viewBinding(ScreenAnimalBinding::bind)
    private val adapter by lazy { AnimalAdapter() }
    private val viewModel: AnimalViewModel by viewModels()
    private var localStorage: LocalStorage? = null

    override fun onViewCreated() {
        localStorage = LocalStorage(requireContext())
        setAdapter()
        observer()

    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.onClickItem = {
            findNavController().navigate(
                AnimalScreenDirections.actionAnimalScreenToAnimalDetailScreen(
                    it
                )
            )
        }

    }

    private fun observer() {

        binding.progressBar.visible()
        viewModel.animalLD.observe(viewLifecycleOwner) {
            adapter.setData(it)
            binding.progressBar.gone()
        }

        viewModel.getAnimals(localStorage!!.language)
    }

}