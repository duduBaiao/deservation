package com.nostalgictouch.deservation.matchers

import android.support.annotation.NonNull
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
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

    fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)

        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position) ?: return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}
