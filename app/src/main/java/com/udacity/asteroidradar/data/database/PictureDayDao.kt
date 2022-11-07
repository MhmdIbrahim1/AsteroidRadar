package com.udacity.asteroidradar.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.data.PictureOfDay
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureDayDao {
    @Query("SELECT * FROM pictureofday")
    fun get(): Flow<PictureOfDay>

    @Transaction
    fun updateData(pic: PictureOfDay): Long {
        deleteAll()
        return insert(pic)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pic: PictureOfDay): Long

    @Query("DELETE FROM pictureofday")
    fun deleteAll()
}