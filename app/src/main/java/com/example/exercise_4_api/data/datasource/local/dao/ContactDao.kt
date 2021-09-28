package com.example.exercise_4_api.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.exercise_4_api.data.model.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertContact(contact: ContactEntity)

    @Delete
    suspend fun deleteContact(contact: ContactEntity)

    @Query("Select * from Contact")
    fun getContacts(): LiveData<List<ContactEntity>>

    @Query("Select id from Contact")
    suspend fun getFavoriteContactsId(): List<String>
}
