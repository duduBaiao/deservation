package com.nostalgictouch.deservation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.nostalgictouch.deservation.data.livedata.TablesLiveData
import com.nostalgictouch.deservation.data.livedata.common.Status
import com.nostalgictouch.deservation.model.Table

class ReservationsViewModel : ViewModel() {

    private var mTablesLiveData = TablesLiveData()

    val reservations: TablesLiveData
        get() = mTablesLiveData

    val loadingStatus: LiveData<Status>
        get() = mTablesLiveData.loadingStatus

    fun loadReservations() {
        val table1 = Table(1, true)
        val table2 = Table(2, false)
        val table3 = Table(3, true)

        mTablesLiveData.value = listOf(
                table1,
                table2,
                table3)

        mTablesLiveData.loadingStatus.value = Status.LOADED
    }
}
