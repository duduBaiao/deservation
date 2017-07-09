package com.nostalgictouch.deservation.data.livedata

import com.nostalgictouch.deservation.DeservationApp
import com.nostalgictouch.deservation.data.livedata.common.BaseLiveData
import com.nostalgictouch.deservation.data.repository.Repository
import com.nostalgictouch.deservation.model.Table
import javax.inject.Inject

class TablesLiveData : BaseLiveData<List<Table>>() {

    @Inject
    lateinit var mRepository: Repository

    init {
        DeservationApp.appComponent.inject(this)
    }
}
