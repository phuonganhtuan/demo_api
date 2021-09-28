package com.example.exercise_4_api.data.repository

import com.example.exercise_4_api.data.datasource.local.dao.ContactDao
import com.example.exercise_4_api.data.model.ContactEntity

class ContactRepositoryImp(private val dao: ContactDao) : ContactRepository {

    override suspend fun deleteContact(contactEntity: ContactEntity) =
        dao.deleteContact(contactEntity)

    override suspend fun insertContact(contactEntity: ContactEntity) =
        dao.insertContact(contactEntity)

    override fun getAllFavoriteContacts() = dao.getContacts()

    override suspend fun getFavoriteContactsId() = dao.getFavoriteContactsId()
}
