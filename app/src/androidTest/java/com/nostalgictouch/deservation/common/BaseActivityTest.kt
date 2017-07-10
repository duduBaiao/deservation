package com.nostalgictouch.deservation.common

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.widget.AutoCompleteTextView
import com.nostalgictouch.deservation.matchers.CustomMatchers.atPosition
import org.junit.After

open class BaseActivityTest {

    private var mIdlingResource: IdlingResource? = null

    fun registerIdlingResource(idlingResource: IdlingResource) {
        mIdlingResource = idlingResource
        Espresso.registerIdlingResources(idlingResource!!)
    }

    fun typeTextOnSearchView(query: String) {
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(query))
    }

    fun clickOnRecyclerViewItem(recyclerViewId: Int, position: Int) {
        onView(withId(recyclerViewId))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }

    fun checkRecyclerViewItemText(recyclerViewId: Int, position: Int, text: String) {
        onView(withId(recyclerViewId))
                .check(matches(atPosition(position, hasDescendant(withText(text)))))
    }

    fun clickOnDialogButtonWithText(text: String) {
        onView(withText(text))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
    }
    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource)
        }
    }
}