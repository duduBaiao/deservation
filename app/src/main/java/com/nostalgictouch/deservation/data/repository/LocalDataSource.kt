package com.nostalgictouch.deservation.data.repository

import android.content.SharedPreferences
import com.nostalgictouch.deservation.data.db.AppDatabase
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(val prefs: SharedPreferences, val db: AppDatabase) : IDataSource {

    companion object {
        val LAST_RESERVATION_LOAD = "LAST_RESERVATION_LOAD"
        val TEN_MINUTES = (1000 * 60 * 10)
    }

    override fun customers(): Observable<List<Customer>> {
        return db.customerDao().findAll()
                .toObservable()
                .doOnNext {
                    if (it.isEmpty()) throw Exception()
                }
    }

    override fun reservations(): Observable<List<TableReservation>> {
        val lastLoadTime = prefs.getLong(LAST_RESERVATION_LOAD, Date().time)

        if (Date().time - lastLoadTime > TEN_MINUTES) {
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

        prefs.edit().putLong(LAST_RESERVATION_LOAD, Date().time).apply()
    }

    fun swapReservationStatus(tableReservation: TableReservation): Completable {

        return Completable.fromAction {
            tableReservation.available = !tableReservation.available
            db.tableReservationDao().update(tableReservation)
        }
    }
}