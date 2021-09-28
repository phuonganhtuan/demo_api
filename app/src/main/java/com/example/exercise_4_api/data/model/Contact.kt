package com.example.exercise_4_api.data.model

data class Contact(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val address: String = "",
    val gender: String = "",
    val phone: Phone,
    var isFavorite: Boolean = false,
) {
    fun toEntity(): ContactEntity =
        ContactEntity(
            id = id,
            name = name,
            email = email,
            address = address,
            gender = gender,
            home = phone.home,
            office = phone.office,
            mobile = phone.mobile,
        )
}
