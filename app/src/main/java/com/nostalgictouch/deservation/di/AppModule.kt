package com.nostalgictouch.deservation.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.nostalgictouch.deservation.data.api.retrofit.ReservationApi
import com.nostalgictouch.deservation.data.db.AppDatabase
import com.nostalgictouch.deservation.data.prefs.Prefs
import com.nostalgictouch.deservation.data.repository.IRepository
import com.nostalgictouch.deservation.data.repository.LocalDataSource
import com.nostalgictouch.deservation.data.repository.RemoteDataSource
import com.nostalgictouch.deservation.data.repository.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Prefs.NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesPrefs(sharedPreferences: SharedPreferences): Prefs {
        return Prefs(sharedPreferences)
    }

    @Singleton
    @Provides
    fun providesDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "deservation-db").build()
    }

    @Singleton
    @Provides
    fun providesReservationApi(): ReservationApi {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/quandoo-assessment/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(ReservationApi::class.java)
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(prefs: Prefs, db: AppDatabase): LocalDataSource {
        return LocalDataSource(prefs, db)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(reservationApi: ReservationApi): RemoteDataSource {
        return RemoteDataSource(reservationApi)
    }

    @Singleton
    @Provides
    fun providesRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): IRepository {
        return Repository(localDataSource, remoteDataSource)
    }
}
