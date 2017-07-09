package com.nostalgictouch.deservation.di

import com.google.gson.GsonBuilder
import com.nostalgictouch.deservation.data.api.retrofit.ReservationApi
import com.nostalgictouch.deservation.data.repository.RemoteDataSource
import com.nostalgictouch.deservation.data.repository.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

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
    fun providesRemoteDataSource(reservationApi: ReservationApi): RemoteDataSource {
        return RemoteDataSource(reservationApi)
    }

    @Singleton
    @Provides
    fun providesRepository(remoteDataSource: RemoteDataSource): Repository {
        return Repository(remoteDataSource)
    }
}
