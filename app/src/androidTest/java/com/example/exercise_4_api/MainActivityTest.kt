package com.example.exercise_4_api

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.exercise_4_api.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun should_display_default_state_after_launched() {
        onView(withId(R.id.textView)).check(matches(withText("Contact List")))
    }

    @Test
    fun should_display_favorite_state() {
        onView(withId(R.id.bottomNavigation)).perform(NavigationViewActions.navigateTo(R.id.favorites))
        onView(withId(R.id.textView)).check(matches(withText("Favorite contacts")))
    }
}
