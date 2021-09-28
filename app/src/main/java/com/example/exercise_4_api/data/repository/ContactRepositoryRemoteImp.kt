package com.example.exercise_4_api.data.repository

import com.example.exercise_4_api.data.datasource.remote.ContactRemoteDataSource

class ContactRepositoryRemoteImp(
    private val dataSource: ContactRemoteDataSource
) :
    ContactRepositoryRemote {

    override suspend fun getAllContact() = dataSource.getAllContact()
}
