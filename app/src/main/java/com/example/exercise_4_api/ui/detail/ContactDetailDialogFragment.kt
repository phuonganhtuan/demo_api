package com.example.exercise_4_api.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.exercise_4_api.R
import com.example.exercise_4_api.data.model.Contact
import com.example.exercise_4_api.databinding.FragmentContactDetailBinding
import kotlinx.android.synthetic.main.fragment_contact_detail.*

class ContactDetailDialogFragment : DialogFragment() {

    private var contact: Contact? = null

    private lateinit var viewBinding: FragmentContactDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = run {
        viewBinding = FragmentContactDetailBinding.inflate(inflater, container, false)
        viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        contact?.let(::displayContact)
        handleEvents()
    }

    private fun displayContact(contact: Contact) = with(viewBinding) {
        textName.text = contact.name
        textEmail.text = contact.email
        textAddress.text = contact.address
        textGender.text = contact.gender
        textMobile.text = context?.getString(R.string.format_mobile, contact.phone.mobile)
        textHome.text = context?.getString(R.string.format_home, contact.phone.home)
        textOffice.text = context?.getString(R.string.format_office, contact.phone.office)
    }

    private fun handleEvents() {
        buttonCall.setOnClickListener {
        }
        buttonMessage.setOnClickListener {
        }
    }

    companion object {
        fun newInstance(contact: Contact) =
            ContactDetailDialogFragment().apply { this.contact = contact }
    }
}
