package com.nostalgictouch.whatshot.utils.test

import android.support.test.espresso.IdlingResource
import com.nostalgictouch.deservation.utils.test.SimpleCountingIdlingResource

object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource
}
