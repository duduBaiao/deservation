package com.nostalgictouch.deservation.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class TableReservation(
        @PrimaryKey
        var id: Int,
        var available: Boolean) {
}
