package com.nostalgictouch.deservation.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nostalgictouch.deservation.model.Customer
import io.reactivex.Flowable

@Dao
interface CustomerDao {

    @Query("SELECT * FROM Customer")
    fun findAll(): Flowable<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(customers: List<Customer>)

}