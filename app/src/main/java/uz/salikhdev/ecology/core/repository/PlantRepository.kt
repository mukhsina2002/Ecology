package uz.salikhdev.ecology.core.repository

import kotlinx.coroutines.Dispatchers
import uz.salikhdev.ecology.core.model.plant.PlantDetailResponse
import uz.salikhdev.ecology.core.model.plant.PlantResponse
import uz.salikhdev.ecology.core.network.ApiClient
import uz.salikhdev.ecology.core.util.ResultWrapper
import uz.salikhdev.ecology.core.util.parseResponse

class PlantRepository {

    private val service = ApiClient.createPlantService()

    suspend fun getPlants(language: String): ResultWrapper<PlantResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPlants(language)
        }
    }

    suspend fun getPlantDetail(
        id: Int,
        language: String
    ): ResultWrapper<PlantDetailResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getPlantDetail(id, language)
        }
    }

}