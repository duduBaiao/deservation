package com.nostalgictouch.deservation.view.common

import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.support.v7.app.AppCompatActivity
import com.nostalgictouch.deservation.utils.test.EspressoIdlingResource

open class BaseActivity : AppCompatActivity() {

    @VisibleForTesting
    val idlingResource: IdlingResource
        get() = EspressoIdlingResource.idlingResource
}
