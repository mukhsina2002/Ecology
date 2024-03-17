package uz.salikhdev.ecology.core.repository

import kotlinx.coroutines.Dispatchers
import uz.salikhdev.ecology.core.model.nature.NatureResponse
import uz.salikhdev.ecology.core.network.ApiClient
import uz.salikhdev.ecology.core.util.ResultWrapper
import uz.salikhdev.ecology.core.util.parseResponse

class NatureRepository {

    private val service = ApiClient.createNatureService()


    suspend fun getNature(language: String): ResultWrapper<NatureResponse?, Any?> {
        return parseResponse(Dispatchers.IO) {
            service.getProducts(language)
        }
    }

}