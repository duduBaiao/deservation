package com.nostalgictouch.deservation.data.livedata

import com.nostalgictouch.deservation.DeservationApp
import com.nostalgictouch.deservation.data.livedata.common.BaseLiveData
import com.nostalgictouch.deservation.data.livedata.common.Status
import com.nostalgictouch.deservation.data.repository.IRepository
import com.nostalgictouch.deservation.model.Customer
import javax.inject.Inject

class CustomersLiveData : BaseLiveData<List<Customer>>() {

    @Inject
    lateinit var mRepository: IRepository

    init {
        DeservationApp.appComponent.inject(this)
    }

    fun loadCustomers() {
        loadingStatus.value = Status.LOADING

        mRepository.customers()
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