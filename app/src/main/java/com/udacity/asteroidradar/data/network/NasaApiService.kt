package com.udacity.asteroidradar.data.network

import retrofit2.http.GET
import retrofit2.http.Query


interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
    ): String

    @GET("planetary/apod")
    suspend fun getPictureOfDay(@Query("api_key") apiKey: String): String
}

