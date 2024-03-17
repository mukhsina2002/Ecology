package uz.salikhdev.ecology.ui.plant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.salikhdev.ecology.core.model.plant.PlantDetailResponse
import uz.salikhdev.ecology.core.model.plant.PlantResponse
import uz.salikhdev.ecology.core.repository.PlantRepository
import uz.salikhdev.ecology.core.util.ResultWrapper

class PlantViewModel : ViewModel() {

    private val repository = PlantRepository()
    val plantLD: MutableLiveData<PlantResponse> = MutableLiveData()
    val plantDetailLD: MutableLiveData<PlantDetailResponse> = MutableLiveData()
    val errorLD: MutableLiveData<String> = MutableLiveData()

    fun getPlants(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val product = repository.getPlants(language)) {
                is ResultWrapper.ErrorResponse -> {
                    errorLD.postValue(product.code.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLD.postValue("NETWORK_ERROR")
                }

                is ResultWrapper.Success -> {
                    product.response?.let {
                        plantLD.postValue(it)
                    }

                }
            }

        }

    }

    fun getPlantDetail(id: Int, language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val product = repository.getPlantDetail(id, language)) {
                is ResultWrapper.ErrorResponse -> {
                    errorLD.postValue(product.code.toString())
                }

                is ResultWrapper.NetworkError -> {
                    errorLD.postValue("NETWORK_ERROR")
                }

                is ResultWrapper.Success -> {
                    product.response?.let {
                        plantDetailLD.postValue(it)
                    }

                }
            }

        }

    }

}