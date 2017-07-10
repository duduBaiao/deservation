package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.data.db.AppDatabase
import com.nostalgictouch.deservation.data.prefs.Prefs
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSource @Inject constructor(val prefs: Prefs, val db: AppDatabase) : IDataSource {

    override fun customers(): Observable<List<Customer>> {
        return db.customerDao().findAll()
                .toObservable()
                .doOnNext {
                    if (it.isEmpty()) throw Exception()
                }
    }

    override fun reservations(): Observable<List<TableReservation>> {
        if (prefs.reservationsExpired()) {
            return Observable.error(Exception())
        }

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

        prefs.saveReservationsLoadTime()
    }

    fun swapReservationStatus(tableReservation: TableReservation): Completable {

        return Completable.fromAction {
            tableReservation.available = !tableReservation.available
            db.tableReservationDao().update(tableReservation)
        }
    }
}