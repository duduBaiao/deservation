package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.data.api.retrofit.ReservationApi
import com.nostalgictouch.deservation.data.api.retrofit.response.CustomerResponse
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val reservationApi: ReservationApi) : IDataSource {

    override fun customers(): Observable<List<Customer>> {
        return reservationApi.customers().flatMap { toCustomerModel(it) }
    }

    override fun reservations(): Observable<List<TableReservation>> {
        return reservationApi.reservations().flatMap { toTableReservationModel(it) }
    }

    fun toCustomerModel(customers: List<CustomerResponse>): Observable<List<Customer>> {
        return Observable.just(customers.map {
            Customer(it.id, it.customerFirstName, it.customerLastName)
        })
    }

    private fun toTableReservationModel(tables: List<Boolean>): Observable<List<TableReservation>> {
        var id  = 1
        return Observable.just(tables.map {
            TableReservation(id++, it)
        })
    }
}