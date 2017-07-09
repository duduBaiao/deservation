package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.data.db.AppDatabase
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSource @Inject constructor(val db: AppDatabase) : IDataSource {

    override fun customers(): Observable<List<Customer>> {
        return db.customerDao().findAll()
                .toObservable()
                .doOnNext {
                    if (it.isEmpty()) throw Exception()
                }
    }

    override fun reservations(): Observable<List<TableReservation>> {
        return db.tableReservationDao().findAll()
                .toObservable()
                .doOnNext {
                    if (it.isEmpty()) throw Exception()
                }
    }

    fun saveCustomers(customers: List<Customer>) {
        db.customerDao().insertAll(customers)
    }

    fun saveReservations(tableReservations: List<TableReservation>) {
        db.tableReservationDao().insertAll(tableReservations)
    }
}