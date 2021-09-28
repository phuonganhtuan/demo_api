package com.example.exercise_4_api.data.datasource.remote

class ContactRemoteDataSourceImp(private val api: ApiService) : ContactRemoteDataSource {

    override suspend fun getAllContact() = api.getAllContacts()
}
