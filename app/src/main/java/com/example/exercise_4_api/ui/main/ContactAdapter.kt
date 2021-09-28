package com.example.exercise_4_api.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise_4_api.R
import com.example.exercise_4_api.data.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(
    private val onContactClick: (Contact) -> Unit,
    private val onFavoriteClick: (Contact, Int) -> Unit,
) :
    ListAdapter<Contact, ContactAdapter.ContactViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(
            itemView,
            onContactClick,
            onFavoriteClick,
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    fun setData(contactList: List<Contact>) {
        submitList(contactList)
        notifyDataSetChanged()
    }

    class ContactViewHolder(
        itemView: View,
        private val onContactClick: (Contact) -> Unit,
        private val onFavoriteClick: (Contact, Int) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {

        private var contact: Contact? = null

        init {
            itemView.setOnClickListener { contact?.let(onContactClick) }
            itemView.imageFavorite.setOnClickListener {
                contact?.let {
                    onFavoriteClick(it, adapterPosition)
                    it.isFavorite = !it.isFavorite
                    displayFavoriteIcon()
                }
            }
        }

        fun bindData(itemData: Contact) {
            contact = itemData
            itemView.apply {
                textName.text = itemData.name
                textEmail.text = itemData.email
                textMobile.text =
                    itemView.context.getString(R.string.format_mobile, itemData.phone.mobile)
            }
            displayFavoriteIcon()
        }

        private fun displayFavoriteIcon() = contact?.let {
            val iconId = if (it.isFavorite) R.drawable.ic_star_24 else R.drawable.ic_star_border_24
            itemView.imageFavorite.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, iconId)
            )
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<Contact>() {

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact) =
        oldItem.id == newItem.id && oldItem.isFavorite == newItem.isFavorite

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
        oldItem.id === newItem.id && oldItem.isFavorite == newItem.isFavorite
}
