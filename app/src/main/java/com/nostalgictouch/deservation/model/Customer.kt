package com.nostalgictouch.deservation.model

data class Customer(
        val id: Int,
        val firstName: String,
        val lastName: String) {

    fun  fullName(): String {
        return firstName + " " + lastName;
    }
}