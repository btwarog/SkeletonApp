package pl.btwarog.skeletonapp

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented userRepos, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under userRepos.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("pl.btwarog.skeletonapp", appContext.packageName)
    }
}
