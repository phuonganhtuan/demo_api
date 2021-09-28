package com.example.exercise_4_api

import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.data.model.ContactEntity
import com.example.exercise_4_api.data.model.Phone
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ContactEntityTest {

    private lateinit var testContactEntity: ContactEntity
    private lateinit var expectedPhone: Phone
    private lateinit var expectedContact: Contact

    @Before
    fun setupTestData() {
        expectedPhone = Phone(
            mobile = "12345",
            office = "23456",
            home = "34567",
        )
        expectedContact = Contact(
            id = "123",
            name = "tuanpa",
            email = "tuanpa@mail",
            address = "HN",
            gender = "male",
            phone = expectedPhone,
            isFavorite = true,
        )
        testContactEntity = ContactEntity(
            id = "123",
            name = "tuanpa",
            email = "tuanpa@mail",
            address = "HN",
            gender = "male",
            mobile = "12345",
            office = "23456",
            home = "34567",
        )
    }

    @Test
    fun entity_to_contact_should_map_success() {
        val resultContact = testContactEntity.toContact()
        assertEquals(resultContact, expectedContact)
    }
}
