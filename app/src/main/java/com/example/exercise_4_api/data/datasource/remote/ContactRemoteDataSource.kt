package com.example.exercise_4_api.data.datasource.remote

import com.example.exercise_4_api.data.model.Contacts

interface ContactRemoteDataSource {

    suspend fun getAllContact(): Contacts
}
