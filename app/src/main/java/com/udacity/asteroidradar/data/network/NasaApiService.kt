package com.udacity.asteroidradar.data.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
    ): String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") apiKey: String): String
}

