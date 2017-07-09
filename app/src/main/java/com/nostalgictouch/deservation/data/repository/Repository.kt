package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.model.Customer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository @Inject constructor(val remoteDataSource: RemoteDataSource) : DataSource {

    override fun customers(): Observable<List<Customer>> {

        return remoteDataSource.customers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}