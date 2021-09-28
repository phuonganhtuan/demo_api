package com.example.exercise_4_api.data.datasource.remote

import com.example.exercise_4_api.data.model.Contacts
import retrofit2.http.GET

interface ApiService {

    @GET("contacts")
    suspend fun getAllContacts(): Contacts
}
