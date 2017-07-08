package com.nostalgictouch.deservation

import android.app.Application
import com.nostalgictouch.deservation.di.AppComponent
import com.nostalgictouch.deservation.di.DaggerAppComponent

class DeservationApp : Application() {

    companion object {
        val appComponent: AppComponent = DaggerAppComponent.builder().build()
    }
}
