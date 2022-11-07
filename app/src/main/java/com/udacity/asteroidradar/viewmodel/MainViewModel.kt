package com.udacity.asteroidradar.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

import androidx.lifecycle.*
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.repo.AsteroidRepository
import com.udacity.asteroidradar.data.database.AsteroidDatabase
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AsteroidDatabase.getInstance(application)
    private val repository = AsteroidRepository(database)

    private val weekAsteroidList = repository.asteroidList
    private val todayAsteroidList = repository.todayAsteroidList

    val pictureOfDay = repository.pictureOfDay
    private val _navigateToSelectedProperty = MutableLiveData<Asteroid?>()
    val asteroidList: MediatorLiveData<List<Asteroid>> = MediatorLiveData()

    val navigateToSelectedProperty: MutableLiveData<Asteroid?>
        get() = _navigateToSelectedProperty

    init {
        getAsteroidData()
    }


    private fun getAsteroidData() {

      try {

          if (hasInternetConnection()) {
              viewModelScope.launch {
                  repository.refreshAsteroidList()
                  repository.getPictureOfTheDate()
                  asteroidList.addSource(weekAsteroidList) {
                      asteroidList.value = it
                  }
              }
          }
      }catch (_:Exception){

      }
    }
    private fun hasInternetConnection():Boolean{
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        )as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)?: return false
        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)-> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)-> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)-> true
            else -> false
        }
    }

    fun displayPropertyDetails(asteroid: Asteroid) {
        _navigateToSelectedProperty.value = asteroid
    }


    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    private fun removeSource() {
        asteroidList.removeSource(todayAsteroidList)
        asteroidList.removeSource(weekAsteroidList)
    }


    fun onTodayAsteroidsClicked() {
        removeSource()
          asteroidList.addSource(todayAsteroidList) {todayData ->
              asteroidList.value = todayData
          }

    }

    fun onViewWeekAsteroidsClicked() {
           removeSource()
           asteroidList.addSource(weekAsteroidList) {weekData ->
               asteroidList.value = weekData
           }

    }

    fun onSavedAsteroidsClicked() {

            removeSource()
            asteroidList.addSource(weekAsteroidList) {savedData ->
                asteroidList.value = savedData
            }

    }


}