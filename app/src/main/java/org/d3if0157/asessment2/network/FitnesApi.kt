package org.d3if0157.asessment2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/syafiqsyauqi/API-Asessment-3/main/Hasil.json"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface FitnesApi {
    @GET("static-api.json")
    suspend fun getHewan(): String
}
object Fitnes {
    val service: FitnesApi by lazy {
        retrofit.create(FitnesApi::class.java)
    }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED }