package com.example.exercise_4_api.data.model

import com.google.gson.annotations.SerializedName

data class Contacts(
    @SerializedName("contacts")
    val contactList: List<Contact>
)
