package com.example.exercise_4_api.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.exercise_4_api.R
import com.example.exercise_4_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private val mainPagerAdapter = PagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupViews()
    }

    private fun setupViews() = with(viewBinding) {
        mainPager.apply {
            offscreenPageLimit = 1
            isUserInputEnabled = false
            adapter = mainPagerAdapter
        }
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.contacts -> {
                    textTitle.text = getString(R.string.title_contact_list)
                    mainPager.currentItem = 0
                    true
                }
                R.id.favorites -> {
                    textTitle.text = getString(R.string.title_favorite_contacts)
                    mainPager.currentItem = 1
                    true
                }
                else -> false
            }
        }
        mainPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomNavigation.selectedItemId =
                    if (position == 0) R.id.contacts else R.id.favorites
            }
        })
    }
}
