package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.data.api.retrofit.ReservationApi
import com.nostalgictouch.deservation.data.api.retrofit.response.CustomerResponse
import com.nostalgictouch.deservation.model.Customer
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val reservationApi: ReservationApi) : DataSource {

    override fun customers(): Observable<List<Customer>> {
        return reservationApi.customers().flatMap {
            toCustomerModel(it)
        }
    }

    fun toCustomerModel(customers: List<CustomerResponse>): Observable<List<Customer>> {
        return Observable.just(customers.map {
            Customer(it.id, it.customerFirstName, it.customerLastName)
        })
    }
}