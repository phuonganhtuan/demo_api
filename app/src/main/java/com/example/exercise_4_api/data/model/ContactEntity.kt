package com.example.exercise_4_api.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class ContactEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val address: String = "",
    val gender: String = "",
    val mobile: String = "",
    val home: String = "",
    val office: String = ""
) {

    fun toContact(): Contact {
        val phone = Phone(
            mobile = mobile,
            home = home,
            office = office,
        )
        return Contact(
            id = id,
            name = name,
            email = email,
            address = address,
            gender = gender,
            phone = phone,
            isFavorite = true,
        )
    }
}
