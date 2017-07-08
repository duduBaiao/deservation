package com.nostalgictouch.deservation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.nostalgictouch.deservation.data.livedata.CustomersLiveData
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.whatshot.viewmodel.trends.Status

class CustomersViewModel : ViewModel() {

    private var mCustomersLiveData = CustomersLiveData()

    val customers: CustomersLiveData
        get() = mCustomersLiveData

    val loadingStatus: LiveData<Status>
        get() = mCustomersLiveData.loadingStatus

    fun loadCustomers() {
        val customer0 = Customer(0, "Marilyn", "Monroe")
        val customer1 = Customer(1, "Abraham", "Lincoln")
        val customer2 = Customer(2, "Mother", "Teresa")
        val customer3 = Customer(3, "John F.", "Kennedy")
        val customer4 = Customer(4, "Martin Luther", "King")
        val customer5 = Customer(5, "Nelson", "Mandela")
        val customer15 = Customer(15, "Elvis", "Presley")
        val customer17 = Customer(17, "Paul", "McCartney")

        mCustomersLiveData.value = listOf(
                customer0,
                customer1,
                customer2,
                customer3,
                customer4,
                customer5,
                customer15,
                customer17)

        mCustomersLiveData.loadingStatus.value = Status.LOADED
    }
}