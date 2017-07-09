package com.nostalgictouch.deservation

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.nostalgictouch.deservation.common.BaseActivityTest
import com.nostalgictouch.deservation.matchers.CustomMatchers.matchToolbarTitle
import com.nostalgictouch.deservation.view.customers.CustomersActivity
import com.nostalgictouch.deservation.view.reservations.ReservationsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CustomersActivityTest : BaseActivityTest() {

    @Rule @JvmField
    var activityTestRule = IntentsTestRule(CustomersActivity::class.java)

    @Before
    fun setup() {
        registerIdlingResource(activityTestRule.activity.idlingResource)
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

    private fun checkMarylinMonroe() {
        checkFirstCustomerFullName("Marilyn Monroe")
    }

    private fun checkMartinLutherKing() {
        checkFirstCustomerFullName("Martin Luther King")
    }

    private fun checkFirstCustomerFullName(fullName: String) {
        checkRecyclerViewItemText(R.id.customersRecyclerView, 0, fullName)
    }
}
