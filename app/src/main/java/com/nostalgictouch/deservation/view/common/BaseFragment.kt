package com.nostalgictouch.deservation.view.common

import android.support.v4.app.Fragment
import android.view.View
import com.nostalgictouch.deservation.R
import com.nostalgictouch.whatshot.utils.extensions.isNetworkAvailable
import com.nostalgictouch.whatshot.utils.test.EspressoIdlingResource
import com.nostalgictouch.whatshot.viewmodel.trends.Status
import kotlinx.android.synthetic.main.fragment_base.*

open class BaseFragment: Fragment() {

    fun updateBaseLayouts(status: Status) {

        if (status == Status.LOADING) loadingLayout.visibility = View.VISIBLE
        else loadingLayout.visibility = View.GONE

        if (status == Status.ERROR) showErrorMessage()
        else hideErrorMessage()

        if (((status == Status.LOADED) || (status == Status.ERROR)) && !EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    private fun showErrorMessage() {
        errorTextView.text = resources.getString(R.string.load_error) +
                if (activity.isNetworkAvailable()) "" else "\n" + resources.getString(R.string.check_internet)

        errorLayout.visibility = View.VISIBLE
    }

    private fun hideErrorMessage() {
        errorLayout.visibility = View.GONE
    }
}