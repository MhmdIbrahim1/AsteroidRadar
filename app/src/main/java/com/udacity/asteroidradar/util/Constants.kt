package com.udacity.asteroidradar.util

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val API_QUERY_DATE_FORMAT = "yyyy-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "" // api key here


    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        return formatter.format(date)
    }

}