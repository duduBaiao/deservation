package com.nostalgictouch.whatshot.ui.views.common

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.support.v7.app.AppCompatActivity
import com.nostalgictouch.whatshot.utils.test.EspressoIdlingResource

open class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private var mLifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = mLifecycleRegistry

    @VisibleForTesting
    val idlingResource: IdlingResource
        get() = EspressoIdlingResource.idlingResource
}
