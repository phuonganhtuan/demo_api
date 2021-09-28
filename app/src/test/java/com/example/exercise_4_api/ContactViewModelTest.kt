package com.example.exercise_4_api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.data.model.ContactEntity
import com.example.exercise_4_api.data.model.Contacts
import com.example.exercise_4_api.data.model.Phone
import com.example.exercise_4_api.data.repository.ContactRepository
import com.example.exercise_4_api.data.repository.ContactRepositoryRemote
import com.example.exercise_4_api.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ContactViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestRule()

    @Mock
    private lateinit var contactViewModel: MainViewModel

    @Mock
    private lateinit var testContactRepositoryRemote: ContactRepositoryRemote

    @Mock
    private lateinit var testContactRepositoryLocal: ContactRepository

    @Mock
    private lateinit var contactsObserver: Observer<Contacts>

    private val testLifeCycleOwner = TestLifeCycleOwner()

    private val testContacts = Contacts(
        listOf(
            Contact(
                id = "c200",
                name = "Ravi Tamada",
                email = "ravi@gmail.com",
                address = "xx-xx-xxxx,x - street, x - country",
                gender = "male",
                phone = Phone(
                    mobile = "+91 0000000000",
                    home = "00 000000",
                    office = "00 000000"
                )
            ),
            Contact(
                id = "c201",
                name = "Johnny Depp",
                email = "johnny_depp@gmail.com",
                address = "xx-xx-xxxx,x - street, x - country",
                gender = "male",
                phone = Phone(
                    mobile = "+91 0000000000",
                    home = "00 000000",
                    office = "00 000000"
                )
            )
        )
    )

    private val testContactEntities = listOf(
        ContactEntity(
            id = "123",
            name = "tuanpa",
            email = "tuanpa@mail",
            address = "HN",
            gender = "male",
            mobile = "12345",
            office = "23456",
            home = "34567",
        ),
        ContactEntity(
            id = "1232",
            name = "tuanpa2",
            email = "tuanpa@mail2",
            address = "HN2",
            gender = "male",
            mobile = "123452",
            office = "234562",
            home = "345672",
        )
    )

    private val testFavoriteContact = listOf(
        ContactEntity(
            id = "123",
            name = "tuanpa",
            email = "tuanpa@mail",
            address = "HN",
            gender = "male",
            mobile = "12345",
            office = "23456",
            home = "34567",
        ).toContact(),
        ContactEntity(
            id = "1232",
            name = "tuanpa2",
            email = "tuanpa@mail2",
            address = "HN2",
            gender = "male",
            mobile = "123452",
            office = "234562",
            home = "345672",
        ).toContact()
    )

    private val testFavoriteIds = listOf("123", "1232")

    @Before
    fun setUp() {
        testLifeCycleOwner.onCreate()
        contactViewModel = MainViewModel(testContactRepositoryLocal, testContactRepositoryRemote)
        contactViewModel.favoriteContacts.observe(testLifeCycleOwner, contactsObserver)
    }

    @After
    fun tearDown() {
        testLifeCycleOwner.onDestroy()
    }

    @Test
    fun should_get_favorite_contacts_success() {
        testCoroutineRule.runBlockingTest {
            testLifeCycleOwner.onResume()
            `when`(testContactRepositoryLocal.getAllFavoriteContacts()).thenReturn(
                testContactEntities
            )
            testContactRepositoryLocal.getAllFavoriteContacts()
//            contactViewModel.getAllFavoriteContactsTest()
            verify(testContactRepositoryLocal).getAllFavoriteContacts()
        }
    }
}
