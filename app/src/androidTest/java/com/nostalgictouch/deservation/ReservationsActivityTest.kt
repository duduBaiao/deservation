package com.nostalgictouch.deservation

import android.content.Context
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.nostalgictouch.deservation.common.BaseActivityTest
import com.nostalgictouch.deservation.data.prefs.Prefs
import com.nostalgictouch.deservation.data.repository.LocalDataSource
import com.nostalgictouch.deservation.matchers.CustomMatchers.matchToolbarTitle
import com.nostalgictouch.deservation.view.reservations.ReservationsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ReservationsActivityTest : BaseActivityTest() {

    @Rule @JvmField
    var activityTestRule = IntentsTestRule(ReservationsActivity::class.java)

    lateinit private var prefs: Prefs

    @Before
    fun setup() {
        registerIdlingResource(activityTestRule.activity.idlingResource)

        prefs = Prefs(activityTestRule.activity.getSharedPreferences(Prefs.NAME, Context.MODE_PRIVATE))

        prefs.saveReservationsLoadTime(0)
    }

    @Test
    fun checkToolbarTitle() {
        matchToolbarTitle(R.string.reservations)
    }

    @Test
    fun clickOnCustomerShouldOpenReservationsActivity() {

        checkRecyclerViewItemText(R.id.tablesRecyclerView, 0, "Reserved")

        clickOnRecyclerViewItem(R.id.tablesRecyclerView, 0)

        checkRecyclerViewItemText(R.id.tablesRecyclerView, 0, "Available")
    }
}
