package com.example.exercise_4_api.ui.main

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exercise_4_api.ui.contacts.ContactListFragment
import com.example.exercise_4_api.ui.contacts.FavoriteContactListFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int) =
        if (position == 0) {
            ContactListFragment()
        } else {
            FavoriteContactListFragment()
        }

    override fun getItemCount() = NUM_PAGE

    companion object {
        private const val NUM_PAGE = 2
    }
}
