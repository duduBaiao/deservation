package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(
        val localDataSource: LocalDataSource,
        val remoteDataSource: RemoteDataSource) : IRepository {

    override fun customers(): Observable<List<Customer>> {

        return localDataSource.customers()
                .onErrorResumeNext(
                        remoteDataSource.customers()
                            .doOnNext { localDataSource.saveCustomers(it) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun reservations(): Observable<List<TableReservation>> {

        return localDataSource.reservations()
                .onErrorResumeNext(
                        remoteDataSource.reservations()
                                .doOnNext { localDataSource.saveReservations(it) })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun toggleReservationStatus(tableReservation: TableReservation): Completable {

        return localDataSource.toggleReservationStatus(tableReservation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}