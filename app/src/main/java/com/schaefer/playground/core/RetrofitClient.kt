package com.schaefer.playground.core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.schaefer.playground.data.service.VenueService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


internal object RetrofitClient {
    private const val BASE_URL = "https://api.foursquare.com"
    internal const val FOUR_SQUARE_ID = "SFTEX4E45MNUAZXMABQOF4NVEBEIOYKE53ZNMIP40RX2S4CK"
    internal const val FOUR_SQUARE_CLIENT = "5AGNN0IDYHURCDITIZX1PAGMFHEO4O0I4VEBLPKMJEPTQPH4"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().apply {
                newBuilder().build()
            }
            chain.proceed(request)
        }
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }
}

object ApiClient {
    val apiService: VenueService by lazy {
        RetrofitClient.retrofit.create(VenueService::class.java)
    }
}