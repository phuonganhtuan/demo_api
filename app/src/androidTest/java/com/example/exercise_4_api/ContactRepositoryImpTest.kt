package com.example.exercise_4_api

import androidx.test.platform.app.InstrumentationRegistry
import com.example.exercise_4_api.data.datasource.local.dao.ContactDao
import com.example.exercise_4_api.data.datasource.local.database.AppDatabase
import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.data.model.Phone
import com.example.exercise_4_api.data.repository.ContactRepositoryImp
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ContactRepositoryImpTest {

    private lateinit var testContactDao: ContactDao
    private lateinit var testContactRepository: ContactRepositoryImp

    private val testPhone = Phone(
        mobile = "12345",
        office = "23456",
        home = "34567",
    )

    private val testContact = Contact(
        id = "123",
        name = "tuanpa",
        email = "tuanpa@mail",
        address = "HN",
        gender = "male",
        phone = testPhone,
        isFavorite = false,
    )

    private val testPhone2 = Phone(
        mobile = "123452",
        office = "234562",
        home = "345672",
    )

    private val testContact2 = Contact(
        id = "1232",
        name = "tuanpa2",
        email = "tuanpa@mail2",
        address = "HN2",
        gender = "male",
        phone = testPhone2,
        isFavorite = false,
    )

    @Before
    fun setup() {
        testContactDao = run {
            AppDatabase.testMode = true
            AppDatabase.invoke(InstrumentationRegistry.getInstrumentation().targetContext)
                .contactDao()
        }
        testContactRepository = ContactRepositoryImp(testContactDao)
    }

    @Test
    fun should_insert_contact_success() {
        runBlocking {
            val entity = testContact.toEntity()
            testContactRepository.insertContact(entity)
            val contacts = testContactRepository.getAllFavoriteContacts()
            assertTrue(contacts.contains(entity))
        }
    }

    @Test
    fun should_delete_contact_success() {
        runBlocking {
            val entity = testContact.toEntity()
            testContactRepository.deleteContact(entity)
            val contacts = testContactRepository.getAllFavoriteContacts()
            assertTrue(!contacts.contains(entity))
        }
    }

    @Test
    fun should_get_favorite_contacts_success() {
        runBlocking {
            val entity = testContact.toEntity()
            val entity2 = testContact2.toEntity()
            testContactRepository.apply {
                insertContact(entity)
                insertContact(entity2)
            }
            val contacts = testContactRepository.getAllFavoriteContacts()
            assertEquals(contacts, listOf(entity, entity2))
        }
    }

    @Test
    fun should_get_favorite_contacts_id_success() {
        runBlocking {
            val contactIds = testContactRepository.getFavoriteContactsId()
            assertEquals(contactIds, listOf(testContact.id, testContact2.id))
        }
    }

}
