package com.udacity.asteroidradar.viewmodel

import android.app.Application
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
        viewModelScope.launch() {
            repository.refreshAsteroidList()
            repository.getPictureOfTheDate()
            asteroidList.addSource(weekAsteroidList) {
                asteroidList.value = it
            }

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
        asteroidList.addSource(todayAsteroidList) {
            asteroidList.value = it
        }

    }

    fun onViewWeekAsteroidsClicked() {
        removeSource()
        asteroidList.addSource(weekAsteroidList) {
            asteroidList.value = it
        }

    }

    fun onSavedAsteroidsClicked() {
        removeSource()
        asteroidList.addSource(weekAsteroidList) {
            asteroidList.value = it
        }

    }

}