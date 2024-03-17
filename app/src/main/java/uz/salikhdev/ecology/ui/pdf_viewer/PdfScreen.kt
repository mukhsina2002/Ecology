package uz.salikhdev.ecology.ui.pdf_viewer

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.base.BaseFragment
import uz.salikhdev.ecology.databinding.ScreenPdfBinding


class PdfScreen : BaseFragment(R.layout.screen_pdf) {
    private val binding by viewBinding(ScreenPdfBinding::bind)
    private val args by navArgs<PdfScreenArgs>()

    override fun onViewCreated() {
        binding.pdfView.initWithUrl(
            url = "https://ecology.salikhdev.uz${args.url}",
            lifecycleCoroutineScope = lifecycleScope,
            lifecycle = lifecycle
        )
    }


}