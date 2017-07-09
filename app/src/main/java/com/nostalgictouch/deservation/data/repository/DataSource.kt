package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.model.Customer
import io.reactivex.Observable

interface DataSource {

    fun customers(): Observable<List<Customer>>
}