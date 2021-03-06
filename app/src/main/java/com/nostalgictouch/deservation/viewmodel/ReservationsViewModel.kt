package com.nostalgictouch.deservation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.nostalgictouch.deservation.data.livedata.TablesLiveData
import com.nostalgictouch.deservation.data.livedata.common.Status
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Completable

class ReservationsViewModel : ViewModel() {

    private var mTablesLiveData = TablesLiveData()

    val reservations: TablesLiveData
        get() = mTablesLiveData

    val loadingStatus: LiveData<Status>
        get() = mTablesLiveData.loadingStatus

    fun loadReservations() {
        mTablesLiveData.loadReservations()
    }

    fun toggleReservationStatus(tableReservation: TableReservation): Completable {
        return mTablesLiveData.toggleReservationStatus(tableReservation)
    }
}
