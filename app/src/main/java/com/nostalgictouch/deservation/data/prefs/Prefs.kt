package com.nostalgictouch.deservation.data.prefs

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class Prefs @Inject constructor(val sharedPreferences: SharedPreferences) : IPrefs {

    companion object {
        val NAME = "deservation-prefs"
    }

    private val LAST_RESERVATION_LOAD = "LAST_RESERVATION_LOAD"
    private val TEN_MINUTES = (1000 * 60 * 10)

    override fun isReservationsExpired(): Boolean {
        val lastLoadTime = sharedPreferences.getLong(LAST_RESERVATION_LOAD, Date().time)

        return (Date().time - lastLoadTime > TEN_MINUTES)
    }

    override fun saveReservationsLoadTime(time: Long) {
        sharedPreferences.edit().putLong(LAST_RESERVATION_LOAD, time).apply()
    }
}