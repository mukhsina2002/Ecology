package uz.salikhdev.ecology.ui.animal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.salikhdev.ecology.core.model.animal.AnimalDetailResponse
import uz.salikhdev.ecology.core.model.animal.AnimalResponse
import uz.salikhdev.ecology.core.repository.AnimalRepository
import uz.salikhdev.ecology.core.util.ResultWrapper

class AnimalViewModel : ViewModel() {

    private val repository = AnimalRepository()
    val animalLD: MutableLiveData<AnimalResponse> = MutableLiveData()
    val animalDetailLD: MutableLiveData<AnimalDetailResponse> = MutableLiveData()
    val errorLD: MutableLiveData<String> = MutableLiveData()

    fun getAnimals(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val product = repository.getAnimals(language)) {
                is ResultWrapper.ErrorResponse -> {
                    errorLD.postValue(product.code.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLD.postValue("NETWORK_ERROR")
                }

                is ResultWrapper.Success -> {
                    product.response?.let {
                        animalLD.postValue(it)
                    }

                }
            }

        }

    }

    fun getAnimalDetail(id: Int, language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val product = repository.getAnimalDetail(id, language)) {
                is ResultWrapper.ErrorResponse -> {
                    errorLD.postValue(product.code.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLD.postValue("NETWORK_ERROR")
                }

                is ResultWrapper.Success -> {
                    product.response?.let {
                        animalDetailLD.postValue(it)
                    }

                }
            }

        }

    }

}