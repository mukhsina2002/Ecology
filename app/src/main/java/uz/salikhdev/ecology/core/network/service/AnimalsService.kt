package uz.salikhdev.ecology.core.network.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.salikhdev.ecology.core.model.animal.AnimalDetailResponse
import uz.salikhdev.ecology.core.model.animal.AnimalResponse

interface AnimalsService {

    @GET("/api/animals/")
    suspend fun getAnimals(@Query("lang") language: String): Response<AnimalResponse?>

    @GET("/api/animals/{id}/")
    suspend fun getAnimalDetail(
        @Path("id") id: Int,
        @Query("lang") language: String
    ): Response<AnimalDetailResponse?>

}