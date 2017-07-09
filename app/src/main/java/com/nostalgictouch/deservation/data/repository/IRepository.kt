package com.nostalgictouch.deservation.data.repository

import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Completable

interface IRepository : IDataSource {
    
    fun swapReservationStatus(tableReservation: TableReservation): Completable
}