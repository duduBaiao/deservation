package com.nostalgictouch.deservation.data.db.dao

import android.arch.persistence.room.*
import com.nostalgictouch.deservation.model.TableReservation
import io.reactivex.Flowable

@Dao
interface TableReservationDao {

    @Query("SELECT * FROM TableReservation")
    fun findAll(): Flowable<List<TableReservation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(tableReservations: List<TableReservation>)

    @Update
    fun update(tableReservation: TableReservation)

    @Query("DELETE FROM TableReservation")
    fun deleteAll()
}