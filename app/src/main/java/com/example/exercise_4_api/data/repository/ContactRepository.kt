package com.example.exercise_4_api.data.repository

import androidx.lifecycle.LiveData
import com.example.exercise_4_api.data.model.ContactEntity

interface ContactRepository {

    fun getAllFavoriteContacts(): LiveData<List<ContactEntity>>

    suspend fun deleteContact(contactEntity: ContactEntity)

    suspend fun insertContact(contactEntity: ContactEntity)

    suspend fun getFavoriteContactsId(): List<String>
}
