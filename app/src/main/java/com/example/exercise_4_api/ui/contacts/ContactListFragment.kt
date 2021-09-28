package com.example.exercise_4_api.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.exercise_4_api.data.datasource.local.database.AppDatabase
import com.example.exercise_4_api.data.datasource.remote.ContactRemoteDataSourceImp
import com.example.exercise_4_api.data.datasource.remote.RetrofitBuilder
import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.data.model.Contacts
import com.example.exercise_4_api.data.repository.ContactRepositoryImp
import com.example.exercise_4_api.data.repository.ContactRepositoryRemoteImp
import com.example.exercise_4_api.databinding.FragmentContactsBinding
import com.example.exercise_4_api.ui.detail.ContactDetailDialogFragment
import com.example.exercise_4_api.ui.main.ContactAdapter
import com.example.exercise_4_api.ui.main.MainViewModel
import com.example.exercise_4_api.utils.CoroutineState
import com.example.exercise_4_api.utils.CustomCacheLM

class ContactListFragment : Fragment() {

    private val contactAdapter by lazy {
        ContactAdapter(::handleContactClick, ::handleFavoriteClick)
    }

    private val contactRepositoryRemote by lazy {
        ContactRepositoryRemoteImp(
            ContactRemoteDataSourceImp(
                RetrofitBuilder.apiService
            )
        )
    }

    private val contactRepositoryLocal by lazy {
        ContactRepositoryImp(
            AppDatabase.invoke(requireContext()).contactDao()
        )
    }

    private val viewModel by lazy { MainViewModel(contactRepositoryLocal, contactRepositoryRemote) }

    private lateinit var viewBinding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = run {
        viewBinding = FragmentContactsBinding.inflate(inflater, container, false)
        viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        observeData()
        handleEvents()
    }

    private fun initData() = with(viewBinding) {
        viewModel.getAllContacts()
        recyclerContact.apply {
            layoutManager = CustomCacheLM(requireContext())
            adapter = contactAdapter
        }
    }

    private fun observeData() = with(viewModel) {
        displayContact.observe(viewLifecycleOwner, {
            when (it.status) {
                CoroutineState.LOADING -> {
                    viewBinding.progressContact.visibility = View.VISIBLE
                }
                CoroutineState.ERROR -> {
                    handleFetchError(it.message.toString())
                }
                CoroutineState.SUCCESS -> {
                    handleFetchedContacts(it.data ?: return@observe)
                }
            }
        })
        favoriteContacts.observe(viewLifecycleOwner, {
            handleFavoriteContacts()
        })
    }

    private fun handleFetchError(message: String) {
        showMessage(message)
        viewBinding.apply {
            layoutRefresh.isRefreshing = false
            progressContact.visibility = View.GONE
        }
    }

    private fun handleFetchedContacts(contacts: Contacts) {
        contacts.contactList.let { contactList ->
            if (contactList.isNotEmpty()) {
                contactAdapter.setData(contactList)
                viewBinding.textEmpty.visibility = View.GONE
            } else {
                viewBinding.apply {
                    textEmpty.visibility = View.VISIBLE
                    progressContact.visibility = View.GONE
                }
            }
        }
        viewBinding.layoutRefresh.isRefreshing = false
    }

    private fun handleEvents() = with(viewBinding) {
        layoutRefresh.setOnRefreshListener {
            initData()
        }
    }

    private fun handleContactClick(contact: Contact) = activity?.supportFragmentManager?.let {
        ContactDetailDialogFragment.newInstance(contact)
            .show(it, ContactDetailDialogFragment::class.java.simpleName)
    }

    private fun handleFavoriteClick(contact: Contact, position: Int = -1) {
        viewModel.handleFavorite(contact)
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
