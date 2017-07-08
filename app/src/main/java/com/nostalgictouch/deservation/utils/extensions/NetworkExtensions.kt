package com.nostalgictouch.whatshot.utils.extensions

import android.content.Context
import android.net.ConnectivityManager

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo

    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}