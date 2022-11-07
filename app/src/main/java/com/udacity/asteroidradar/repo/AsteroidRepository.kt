package com.udacity.asteroidradar.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.squareup.moshi.Moshi
import com.udacity.asteroidradar.util.parseAsteroidsJsonResult
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.data.PictureOfDay
import com.udacity.asteroidradar.data.database.AsteroidDatabase
import com.udacity.asteroidradar.data.network.NasaApi
import com.udacity.asteroidradar.util.Constants.API_KEY
import com.udacity.asteroidradar.util.Constants.getCurrentDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase) {


    val asteroidList: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getAll().asLiveData()

    val todayAsteroidList: LiveData<List<Asteroid>>
        get() = database.asteroidDao().getTodayAsteroid(getCurrentDate()).asLiveData()

    val pictureOfDay: LiveData<PictureOfDay>
        get() = database.pictureDao().get().asLiveData()


    suspend fun refreshAsteroidList() {
        withContext(Dispatchers.IO) {
            try {
                val asteroid = NasaApi.retrofitService.getAsteroids()
                val json = JSONObject(asteroid)
                val data = parseAsteroidsJsonResult(json)
                database.asteroidDao().updateData(data)
                Log.e("data", "")

            } catch (e: Exception) {
                Log.e("data", "")
            }
        }
    }


    suspend fun getPictureOfTheDate() {
        withContext(Dispatchers.IO) {
            try {
                val response = NasaApi.retrofitService.getPictureOfDay(
                   API_KEY
                )
                val pic = Moshi.Builder()
                    .build()
                    .adapter(PictureOfDay::class.java)
                    .fromJson(response)
                    ?:
                    // Return an empty picture
                    PictureOfDay(-1, "image", "", "")
                database.pictureDao().updateData(pic)
                Log.e("data", "")

            } catch (e: Exception) {
                Log.e("data", "")

            }

        }
    }



}