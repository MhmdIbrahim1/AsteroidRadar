package com.udacity.asteroidradar.data.database

import androidx.room.*
import com.udacity.asteroidradar.data.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM Asteroid ORDER BY date(closeApproachDate) DESC")
    fun getAll(): Flow<List<Asteroid>>


    @Query("SELECT * FROM Asteroid WHERE closeApproachDate <=:date ORDER BY date(closeApproachDate) DESC ")
    fun getTodayAsteroid(date: String): Flow<List<Asteroid>>

    @Transaction
    fun updateData(users: List<Asteroid>): List<Long> {
        deleteAll()
        return insertAll(users)
    }

    /** OnConflictStrategy  means that whenever we fetch a new data from our API, we want to replace the old one.*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<Asteroid>): List<Long>

    @Query("DELETE FROM Asteroid")
    fun deleteAll()

}