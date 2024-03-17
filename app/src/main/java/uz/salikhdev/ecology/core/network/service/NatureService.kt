package uz.salikhdev.ecology.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.salikhdev.ecology.core.model.nature.NatureResponse

interface NatureService {

    @GET("/api/nature/?format=json")
    suspend fun getProducts(@Query("lang") language: String): Response<NatureResponse?>

}