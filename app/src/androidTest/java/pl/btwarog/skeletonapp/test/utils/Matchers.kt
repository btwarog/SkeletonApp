package pl.btwarog.skeletonapp.test.utils

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

class Matchers {
    fun <T : Activity> nextOpenActivityIs(clazz: Class<T>, matcher: Matcher<Intent>? = null) {
        if (matcher != null) {
            intended(
                    CoreMatchers.allOf(
                            IntentMatchers.hasComponent(clazz.name),
                            matcher
                    )
            )
        } else {
            intended(
                    IntentMatchers.hasComponent(clazz.name)
            )
        }
    }

    fun nextIntentHasAction(action: String) {
        intended(IntentMatchers.hasAction(action))
    }

    fun <T : Any> nextIntentHas(key: String, value: T) {
        intended(IntentMatchers.hasExtra(key, value))
    }
}