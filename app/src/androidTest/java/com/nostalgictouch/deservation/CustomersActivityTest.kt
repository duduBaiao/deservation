package com.nostalgictouch.deservation

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.nostalgictouch.deservation.matchers.CustomMatchers
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
        CustomMatchers.matchToolbarTitle(R.string.pick_a_customer)
    }

    @Test
    fun clickOnCustomerShouldOpenReservationsActivity() {

        Espresso.onView(ViewMatchers.withId(R.id.customersRecyclerView))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
                )

        Intents.intended(IntentMatchers.hasComponent(ReservationsActivity::class.java.name))
    }

    @After
    fun unregisterIdlingResource() {
        if (idlingResource != null) {
            Espresso.unregisterIdlingResources(idlingResource)
        }
    }
}
