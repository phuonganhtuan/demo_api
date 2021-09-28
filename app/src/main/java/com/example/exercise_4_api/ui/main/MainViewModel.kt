package com.example.exercise_4_api.ui.main

import androidx.lifecycle.*
import com.example.exercise_4_api.base.Result
import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.data.model.Contacts
import com.example.exercise_4_api.data.repository.ContactRepository
import com.example.exercise_4_api.data.repository.ContactRepositoryRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val localRepository: ContactRepository,
    private val remoteRepository: ContactRepositoryRemote
) : ViewModel() {

    private val contacts = MutableLiveData<Result<Contacts>>()

    val displayContact: LiveData<Result<Contacts>> get() = _displayContacts
    private val _displayContacts = MutableLiveData<Result<Contacts>>()

    val favoriteContacts = localRepository.getAllFavoriteContacts()

    fun getAllContacts() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _displayContacts.postValue(Result.loading(data = contacts.value?.data))
            try {
                contacts.postValue(Result.success(data = remoteRepository.getAllContact()))
                handleFavoriteContacts()
            } catch (exception: Exception) {
                _displayContacts.postValue(
                    Result.error(data = null, message = exception.message ?: "Error Occurred!")
                )
            }
        }
    }

    fun handleFavoriteContacts() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val ids = localRepository.getFavoriteContactsId()
            val contacts = contacts.value
            contacts?.let {
                it.data?.contactList?.forEach { contact ->
                    contact.isFavorite = ids.contains(contact.id)
                    _displayContacts.postValue(it)
                }
            }
        }
    }

    fun handleFavorite(contact: Contact) {
        if (contact.isFavorite) {
            viewModelScope.launch {
                localRepository.deleteContact(contact.toEntity())
            }
        } else {
            viewModelScope.launch {
                localRepository.insertContact(contact.toEntity())
            }
        }
    }
}
