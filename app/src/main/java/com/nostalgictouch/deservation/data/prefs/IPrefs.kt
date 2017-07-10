package com.nostalgictouch.deservation.data.prefs

import java.util.*

interface IPrefs {

    fun isReservationsExpired(): Boolean

    fun saveReservationsLoadTime(time: Long = Date().time)
}