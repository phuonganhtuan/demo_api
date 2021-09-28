package com.example.exercise_4_api.data.model

import com.google.gson.annotations.SerializedName

data class Phone(
    @SerializedName("mobile")
    val mobile: String = "",
    @SerializedName("home")
    val home: String = "",
    @SerializedName("office")
    val office: String = "",
)
