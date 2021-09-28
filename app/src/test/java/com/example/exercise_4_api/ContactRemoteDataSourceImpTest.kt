package com.example.exercise_4_api

import com.example.exercise_4_api.data.datasource.remote.ContactRemoteDataSourceImp
import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.data.model.Contacts
import com.example.exercise_4_api.data.model.Phone
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import java.net.HttpURLConnection
import org.junit.Assert.*

class ContactRemoteDataSourceImpTest {

    private val mockContactApi = MockRetrofitService.apiService

    private val contactRemoteDataSource = ContactRemoteDataSourceImp(mockContactApi)

    private val testResponse = "{\n" +
            "        \"contacts\": [\n" +
            "        {\n" +
            "            \"id\": \"c200\",\n" +
            "            \"name\": \"Ravi Tamada\",\n" +
            "            \"email\": \"ravi@gmail.com\",\n" +
            "            \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "            \"gender\" : \"male\",\n" +
            "            \"phone\": {\n" +
            "            \"mobile\": \"+91 0000000000\",\n" +
            "            \"home\": \"00 000000\",\n" +
            "            \"office\": \"00 000000\"\n" +
            "        }\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\": \"c201\",\n" +
            "            \"name\": \"Johnny Depp\",\n" +
            "            \"email\": \"johnny_depp@gmail.com\",\n" +
            "            \"address\": \"xx-xx-xxxx,x - street, x - country\",\n" +
            "            \"gender\" : \"male\",\n" +
            "            \"phone\": {\n" +
            "            \"mobile\": \"+91 0000000000\",\n" +
            "            \"home\": \"00 000000\",\n" +
            "            \"office\": \"00 000000\"\n" +
            "        }\n" +
            "        }\n" +
            "        ]\n" +
            "    }"

    private val expectResponseObject = Contacts(
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

    @Test
    fun should_get_correct_data_from_server() {
        runBlocking {
            val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(testResponse)
            MockRetrofitService.mockWebServer.enqueue(response)
            val contacts = contactRemoteDataSource.getAllContact()
            assertEquals(contacts, expectResponseObject)
        }
    }
}
