package com.nostalgictouch.deservation.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nostalgictouch.deservation.model.Customer

@Dao
interface CustomerDao {

    @Query("SELECT * FROM Customer")
    abstract fun findAll(): List<Customer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(customer: Customer)
}