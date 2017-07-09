package com.nostalgictouch.deservation.data.api.retrofit

import com.nostalgictouch.deservation.data.api.retrofit.response.CustomerResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ReservationApi {

    @GET("customer-list.json")
    fun customers(): Observable<List<CustomerResponse>>

    @GET("table-map.json")
    fun reservations(): Observable<List<Boolean>>
}
