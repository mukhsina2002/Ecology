package uz.salikhdev.ecology.ui.natural_resource

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.salikhdev.ecology.core.model.nature.NatureResponse
import uz.salikhdev.ecology.core.repository.NatureRepository
import uz.salikhdev.ecology.core.util.ResultWrapper

class NatureViewModel : ViewModel() {

    private val repository = NatureRepository()
    val natureLD: MutableLiveData<NatureResponse> = MutableLiveData()
    val errorLD: MutableLiveData<String> = MutableLiveData()

    fun getNature(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val product = repository.getNature(language)) {
                is ResultWrapper.ErrorResponse -> {
                    errorLD.postValue(product.code.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLD.postValue("NETWORK_ERROR")
                }

                is ResultWrapper.Success -> {
                    product.response?.let {
                        natureLD.postValue(it)
                    }

                }
            }

        }

    }


}