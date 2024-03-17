package uz.salikhdev.ecology.core.repository

import kotlinx.coroutines.Dispatchers
import uz.salikhdev.ecology.core.model.animal.AnimalDetailResponse
import uz.salikhdev.ecology.core.model.animal.AnimalResponse
import uz.salikhdev.ecology.core.network.ApiClient
import uz.salikhdev.ecology.core.util.ResultWrapper
import uz.salikhdev.ecology.core.util.parseResponse

class AnimalRepository {

    private val service = ApiClient.createAnimalService()

    suspend fun getAnimals(language:String): ResultWrapper<AnimalResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getAnimals(language)
        }
    }

    suspend fun getAnimalDetail(id: Int,language:String): ResultWrapper<AnimalDetailResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getAnimalDetail(id ,language)
        }
    }

}