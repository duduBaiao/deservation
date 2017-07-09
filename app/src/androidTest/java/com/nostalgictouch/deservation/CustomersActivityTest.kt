package com.nostalgictouch.deservation

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.widget.AutoCompleteTextView
import com.nostalgictouch.deservation.matchers.CustomMatchers.atPosition
import com.nostalgictouch.deservation.matchers.CustomMatchers.matchToolbarTitle
import com.nostalgictouch.deservation.view.customers.CustomersActivity
import com.nostalgictouch.deservation.view.reservations.ReservationsActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class CustomersActivityTest {

    @Rule @JvmField
    var activityTestRule = IntentsTestRule(CustomersActivity::class.java)

    private var idlingResource: IdlingResource? = null

    @Before
    fun registerIdlingResource() {
        idlingResource = activityTestRule.activity.idlingResource

        Espresso.registerIdlingResources(idlingResource!!)
    }

    @Test
    fun checkToolbarTitle() {
        matchToolbarTitle(R.string.pick_a_customer)
    }

    @Test
    fun clickOnCustomerShouldOpenReservationsActivity() {

        onView(withId(R.id.customersRecyclerView))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
                )

        Intents.intended(IntentMatchers.hasComponent(ReservationsActivity::class.java.name))
    }

    @Test
    fun shouldFilterCustomer() {

        checkMarylinMonroe()

        onView(withId(R.id.search)).perform(click())

        typeTextOnSearchView("mar")

        checkMarylinMonroe()

        typeTextOnSearchView("t")

        checkMartinLutherKing()
    }

    private fun typeTextOnSearchView(query: String) {
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(query))
    }

    private fun checkMarylinMonroe() {
        checkFirstCustomerFullName("Marilyn Monroe")
    }

    private fun checkMartinLutherKing() {
        checkFirstCustomerFullName("Martin Luther King")
    }

    private fun checkFirstCustomerFullName(fullName: String) {
        onView(withId(R.id.customersRecyclerView))
                .check(matches(atPosition(0, hasDescendant(withText(fullName)))))
    }

    @After
    fun unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource)
        }
    }
}
