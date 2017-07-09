package com.nostalgictouch.deservation.data.livedata

import com.nostalgictouch.deservation.DeservationApp
import com.nostalgictouch.deservation.data.livedata.common.BaseLiveData
import com.nostalgictouch.deservation.data.livedata.common.Status
import com.nostalgictouch.deservation.data.repository.Repository
import com.nostalgictouch.deservation.model.TableReservation
import javax.inject.Inject

class TablesLiveData : BaseLiveData<List<TableReservation>>() {

    @Inject
    lateinit var mRepository: Repository

    init {
        DeservationApp.appComponent.inject(this)
    }

    fun loadReservations() {
        loadingStatus.value = Status.LOADING

        mRepository.reservations()
                .subscribe(
                        {
                            this.value = it
                            loadingStatus.value = Status.LOADED
                        },
                        {
                            loadingStatus.value = Status.ERROR
                        })
    }
}
