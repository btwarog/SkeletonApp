package pl.btwarog.skeletonapp.test.utils

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

class Events {
    fun clickOnView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }

    fun clickOnViewWithText(@StringRes stringId: Int) {
        onView(withText(stringId)).perform(click())
    }

    fun clickOnViewWithText(text: String) {
        onView(withText(text)).perform(click())
    }
}