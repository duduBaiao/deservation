package com.nostalgictouch.deservation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nostalgictouch.deservation.data.db.AppDatabase
import com.nostalgictouch.deservation.data.prefs.Prefs
import com.nostalgictouch.deservation.data.repository.LocalDataSource
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RepositoryTest {

    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mDb: AppDatabase

    lateinit var mLocalDataSource: LocalDataSource

    @Before
    fun createDb() {
        mDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        val prefs = Prefs((InstrumentationRegistry.getContext().getSharedPreferences(Prefs.NAME, Context.MODE_PRIVATE)))

        mLocalDataSource = LocalDataSource(prefs, mDb)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDb.close()
    }

    @Test
    fun shouldGetErrorWhenTheDatabaseIsEmpty() {

        mLocalDataSource.customers().test()
                .assertError(Exception::class.java)

        mLocalDataSource.reservations().test()
                .assertError(Exception::class.java)
    }

    @Test
    fun shouldFindCustomersWhenTheTableIsNotEmpty() {
        mLocalDataSource.saveCustomers(listOf(Customer(0, "Marilyn", "Monroe")))

        mLocalDataSource.customers().test()
                .assertValue {
                    customers ->
                    customers[0].fullName().equals("Marilyn Monroe")
                }
    }

    @Test
    fun shouldFindReservationsWhenTheTableIsNotEmpty() {
        mLocalDataSource.saveReservations(listOf(TableReservation(0, true)))

        mLocalDataSource.reservations().test()
                .assertValue {
                    tableReservation ->
                    tableReservation[0].available
                }
    }
}