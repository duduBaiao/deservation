package com.nostalgictouch.whatshot.matchers

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.v7.widget.Toolbar
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`

object CustomMatchers {

    fun matchToolbarTitle(id: Int): ViewInteraction {

        val title = InstrumentationRegistry.getTargetContext().getString(id)

        return onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(`is`(title))))
    }

    fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> {

        return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {

            public override fun matchesSafely(toolbar: Toolbar): Boolean {
                return textMatcher.matches(toolbar.title)
            }

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
    }
}
