package com.nostalgictouch.deservation.data.prefs

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class Prefs @Inject constructor(val sharedPreferences: SharedPreferences) {

    companion object {
        val NAME = "deservation-prefs"
    }

    private val LAST_RESERVATION_LOAD = "LAST_RESERVATION_LOAD"
    private val TEN_MINUTES = (1000 * 60 * 10)

    fun reservationsExpired(): Boolean {
        val lastLoadTime = sharedPreferences.getLong(LAST_RESERVATION_LOAD, Date().time)

        return (Date().time - lastLoadTime > TEN_MINUTES)
    }

    fun saveReservationsLoadTime(time: Long = Date().time) {
        sharedPreferences.edit().putLong(LAST_RESERVATION_LOAD, time).apply()
    }
}