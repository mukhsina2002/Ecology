package uz.salikhdev.ecology.ui.natural_resource

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.adapter.NatureAdapter
import uz.salikhdev.ecology.core.base.BaseFragment
import uz.salikhdev.ecology.core.cache.LocalStorage
import uz.salikhdev.ecology.core.util.gone
import uz.salikhdev.ecology.core.util.visible
import uz.salikhdev.ecology.databinding.ScreenNatureBinding


class NatureScreen : BaseFragment(R.layout.screen_nature) {

    private val binding by viewBinding(ScreenNatureBinding::bind)
    private val adapter by lazy { NatureAdapter() }
    private val viewModel: NatureViewModel by viewModels()
    private var localStorage: LocalStorage? = null


    override fun onViewCreated() {
        localStorage = LocalStorage(requireContext())

        setAdapter()
        observer()
        loadAction()

    }

    private fun loadAction() {

        adapter.onClickItem = {
            findNavController().navigate(NatureScreenDirections.actionNatureScreenToPdfScreen(it))
        }

    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun observer() {

        binding.progressBar.visible()
        viewModel.getNature(localStorage!!.language)

        viewModel.natureLD.observe(viewLifecycleOwner) {
            adapter.setData(it)
            binding.progressBar.gone()
        }

    }

}