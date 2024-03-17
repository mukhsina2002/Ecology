package uz.salikhdev.ecology.core.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.salikhdev.ecology.core.app.App
import uz.salikhdev.ecology.core.network.service.AnimalsService
import uz.salikhdev.ecology.core.network.service.PlantService
import uz.salikhdev.ecology.core.network.service.NatureService

object ApiClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ecology.salikhdev.uz/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    private fun OkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //.addInterceptor(chuckerInterceptor())
            .addInterceptor(interceptor())
            .build()
    }

    private fun interceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val builder: Request.Builder = request.newBuilder()
            builder
                .addHeader("Content-Type", "application/json")
            val response = chain.proceed(builder.build())
            response
        }
    }

    private fun chuckerInterceptor() = ChuckerInterceptor.Builder(App.context!!)
        .collector(chuckerCollector())
        .maxContentLength(250_000L)
        .alwaysReadResponseBody(true)
        .build()

    private fun chuckerCollector() = ChuckerCollector(
        context = App.context!!,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )


    fun createNatureService(): NatureService {
        return getRetrofit().create(NatureService::class.java)
    }

    fun createAnimalService(): AnimalsService {
        return getRetrofit().create(AnimalsService::class.java)
    }

    fun createPlantService(): PlantService {
        return getRetrofit().create(PlantService::class.java)
    }

}