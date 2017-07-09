package com.nostalgictouch.deservation

import android.app.Application
import com.nostalgictouch.deservation.di.AppComponent
import com.nostalgictouch.deservation.di.AppModule
import com.nostalgictouch.deservation.di.DaggerAppComponent

class DeservationApp : Application() {

    companion object {
        lateinit private var mAppComponent: AppComponent

        val appComponent: AppComponent
            get() = mAppComponent
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}
