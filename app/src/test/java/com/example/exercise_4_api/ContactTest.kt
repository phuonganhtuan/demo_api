package com.example.exercise_4_api

import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.data.model.ContactEntity
import com.example.exercise_4_api.data.model.Phone
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ContactTest {

    private lateinit var testPhone: Phone
    private lateinit var testContact: Contact
    private lateinit var expectedContactEntity: ContactEntity

    @Before
    fun setupTestData() {
        testPhone = Phone(
            mobile = "12345",
            office = "23456",
            home = "34567",
        )
        testContact = Contact(
            id = "123",
            name = "tuanpa",
            email = "tuanpa@mail",
            address = "HN",
            gender = "male",
            phone = testPhone,
            isFavorite = false,
        )
        expectedContactEntity = ContactEntity(
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
    fun contact_to_entity_should_map_success() {
        val resultEntity = testContact.toEntity()
        assertEquals(resultEntity, expectedContactEntity)
    }
}
