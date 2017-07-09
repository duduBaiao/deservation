package com.nostalgictouch.deservation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.nostalgictouch.deservation.data.livedata.CustomersLiveData
import com.nostalgictouch.deservation.data.livedata.common.Status

class CustomersViewModel : ViewModel() {

    private var mCustomersLiveData = CustomersLiveData()

    val customers: CustomersLiveData
        get() = mCustomersLiveData

    val loadingStatus: LiveData<Status>
        get() = mCustomersLiveData.loadingStatus

    fun loadCustomers() {
        mCustomersLiveData.loadCustomers()
    }
}