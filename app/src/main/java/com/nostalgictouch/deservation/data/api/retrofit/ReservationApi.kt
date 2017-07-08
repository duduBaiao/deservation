package com.nostalgictouch.whatshot.data.api.retrofit

import com.nostalgictouch.deservation.data.api.retrofit.response.Customer
import retrofit2.Call
import retrofit2.http.GET

interface ReservationApi {

    @GET("customer-list.json")
    fun customers(): Call<List<Customer>>

    @GET("table-map.json")
    fun reservations(): Call<List<Boolean>>
}
