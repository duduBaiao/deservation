package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.data.api.retrofit.ReservationApi
import com.nostalgictouch.deservation.data.api.retrofit.response.CustomerResponse
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.Table
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val reservationApi: ReservationApi) : DataSource {

    override fun customers(): Observable<List<Customer>> {
        return reservationApi.customers().flatMap { toCustomerModel(it) }
    }

    fun toCustomerModel(customers: List<CustomerResponse>): Observable<List<Customer>> {
        return Observable.just(customers.map {
            Customer(it.id, it.customerFirstName, it.customerLastName)
        })
    }

    override fun reservations(): Observable<List<Table>> {
        return reservationApi.reservations().flatMap { toTableModel(it) }
    }

    private fun toTableModel(tables: List<Boolean>): Observable<List<Table>> {
        var id  = 1
        return Observable.just(tables.map {
            Table(id++, it)
        })
    }
}