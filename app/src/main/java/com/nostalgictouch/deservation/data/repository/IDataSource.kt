package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Observable

interface IDataSource {

    fun customers(): Observable<List<Customer>>

    fun reservations(): Observable<List<TableReservation>>
}