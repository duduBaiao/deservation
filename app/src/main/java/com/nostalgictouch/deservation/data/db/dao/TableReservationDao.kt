package com.nostalgictouch.deservation.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.nostalgictouch.deservation.model.TableReservation

@Dao
interface TableReservationDao {

    @Query("SELECT * FROM TableReservation")
    abstract fun findAll(): List<TableReservation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(tableReservation: TableReservation)

    @Query("DELETE FROM TableReservation")
    abstract fun deleteAll()
}