package uz.salikhdev.ecology.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.salikhdev.ecology.core.model.plant.PlantDetailResponse
import uz.salikhdev.ecology.core.model.plant.PlantResponse

interface PlantService {

    @GET("/api/plants/")
    suspend fun getPlants(@Query("lang") language: String): Response<PlantResponse?>

    @GET("/api/plants/{id}/")
    suspend fun getPlantDetail(
        @Path("id") id: Int,
        @Query("lang") language: String
    ): Response<PlantDetailResponse?>

}