package com.example.exercise_4_api.data.repository

import com.example.exercise_4_api.data.model.Contacts

interface ContactRepositoryRemote {

    suspend fun getAllContact(): Contacts
}
