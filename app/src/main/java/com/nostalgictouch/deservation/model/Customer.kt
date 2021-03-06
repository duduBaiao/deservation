package com.nostalgictouch.deservation.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Customer(
        @PrimaryKey
        var id: Int,
        var firstName: String,
        var lastName: String) {

    fun fullName(): String {
        return firstName + " " + lastName
    }
}