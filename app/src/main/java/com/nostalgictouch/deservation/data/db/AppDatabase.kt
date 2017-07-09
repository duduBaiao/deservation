package com.nostalgictouch.deservation.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.nostalgictouch.deservation.data.db.dao.CustomerDao
import com.nostalgictouch.deservation.data.db.dao.TableReservationDao
import com.nostalgictouch.deservation.model.Customer
import com.nostalgictouch.deservation.model.TableReservation

@Database(entities = arrayOf(Customer::class, TableReservation::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    abstract fun tableReservationDao(): TableReservationDao
}