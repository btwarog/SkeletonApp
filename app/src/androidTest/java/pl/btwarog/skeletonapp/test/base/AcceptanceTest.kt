package pl.btwarog.skeletonapp.test.base

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import pl.btwarog.skeletonapp.test.utils.Events
import pl.btwarog.skeletonapp.test.utils.Matchers

@LargeTest
@RunWith(AndroidJUnit4::class)
abstract class AcceptanceTest<T : Activity>(clazz: Class<T>, initialTouchMode: Boolean, initialLaunchActivity: Boolean) {

    @Rule
    @JvmField
    val testRule: ActivityTestRule<T> = object : IntentsTestRule<T>(clazz, initialTouchMode, initialLaunchActivity) {
        override fun getActivityIntent(): Intent {
            return createIntent()
        }
    }

    abstract fun createIntent(): Intent

    val checkThat: Matchers = Matchers()
    val events: Events = Events()
}