package com.nostalgictouch.deservation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.nostalgictouch.deservation.data.livedata.CustomersLiveData
import com.nostalgictouch.deservation.data.livedata.TablesLiveData
import com.nostalgictouch.deservation.data.livedata.common.Status
import com.nostalgictouch.deservation.data.repository.IRepository
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LiveDataTest {

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mCustomerLiveData: CustomersLiveData
    lateinit var mTablesLiveData: TablesLiveData
    lateinit var mRepositoryMock: IRepository

    @Before
    fun setup() {
        mRepositoryMock = mock<IRepository>()

        val customers = listOf(Customer(0, "Marilyn", "Monroe"))
        whenever(mRepositoryMock.customers())
                .thenReturn(Observable.just(customers))

        val reservations = listOf(TableReservation(0, true))
        whenever(mRepositoryMock.reservations())
                .thenReturn(Observable.just(reservations))

        mCustomerLiveData = CustomersLiveData()
        mCustomerLiveData.mRepository = mRepositoryMock

        mTablesLiveData = TablesLiveData()
        mTablesLiveData.mRepository = mRepositoryMock
    }

    @Test
    fun initialStatusShouldBeNone() {

        assertEquals(Status.NONE, mCustomerLiveData.loadingStatus.value)
        assertEquals(Status.NONE, mTablesLiveData.loadingStatus.value)
    }

    @Test
    fun shouldLoadCustomers() {

        mCustomerLiveData.loadCustomers()

        assertEquals(Status.LOADED, mCustomerLiveData.loadingStatus.value)

        assertNotNull(mCustomerLiveData.value)

        mCustomerLiveData.value?.let {
            assertEquals("Marilyn Monroe", it.get(0).fullName())
        }
    }

    @Test
    fun shouldLoadReservations() {

        mTablesLiveData.loadReservations()

        assertEquals(Status.LOADED, mTablesLiveData.loadingStatus.value)

        assertNotNull(mTablesLiveData.value)

        mTablesLiveData.value?.let {
            assertEquals(true, it.get(0).available)
        }
    }
}