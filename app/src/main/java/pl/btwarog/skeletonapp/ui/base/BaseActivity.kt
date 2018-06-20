package pl.btwarog.skeletonapp.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import icepick.Icepick
import nucleus5.factory.PresenterFactory
import nucleus5.factory.ReflectionPresenterFactory
import nucleus5.presenter.Presenter
import nucleus5.view.PresenterLifecycleDelegate
import nucleus5.view.ViewWithPresenter
import pl.btwarog.skeletonapp.R
import pl.btwarog.skeletonapp.injection.Injector
import timber.log.Timber
import javax.inject.Inject

/**
 *  Basic Activity class, inherited by most of Activities in project. Has standard functionality,
 *  save state with Icepick, inject intent passed data with Dart, use Dagger for injection and has
 *  connection with Presenter.
 */
abstract class BaseActivity<P : Presenter<*>> : AppCompatActivity(),
        HasSupportFragmentInjector,
        ViewWithPresenter<P> {


    @Inject
    protected lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    /**
     * Provides default title of screen visible on top toolbar
     *
     * @return
     */
    protected abstract val screenTitle: String
    /**
     * Provides info whether activity should have back option
     *
     * @return
     */
    protected open val shouldHaveBackBtn = false

    /**
     * Provides layoutId of visible screen
     *
     * @return
     */
    @get:IntegerRes
    protected abstract val layoutId: Int

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        if (savedInstanceState != null) {
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_STATE_KEY))
        }
        setInjectingPresenterFactory()
        super.onCreate(savedInstanceState)
        Icepick.restoreInstanceState(this, savedInstanceState)
        setContentView(layoutId)
        Timber.d("onCreate")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState())
        Icepick.saveInstanceState(this, outState)
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return supportFragmentInjector
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        toolbar?.run {
            setSupportActionBar(this)
            val actionBar = supportActionBar
            actionBar?.apply {
                setDisplayHomeAsUpEnabled(shouldHaveBackBtn)
                setHomeButtonEnabled(shouldHaveBackBtn)
                setDisplayShowTitleEnabled(true)
                title = screenTitle
            }

        }
    }

    override fun onResume() {
        super.onResume()
        presenterDelegate.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        presenterDelegate.onDropView()
    }

    override fun onDestroy() {
        Timber.d("%s.onDestroy", javaClass.simpleName)
        super.onDestroy()
        presenterDelegate.onDestroy(!isChangingConfigurations)
    }

    /**
     * Sets presenter factory with injects presenters.
     */
    private fun setInjectingPresenterFactory() {

        val superFactory = presenterDelegate.presenterFactory

        if (superFactory == null)
            presenterDelegate.presenterFactory = null
        else {
            presenterDelegate.presenterFactory = PresenterFactory<P> {
                val presenter = superFactory.createPresenter()
                if (application is Injector) {
                    (application as Injector).inject(presenter)
                }
                presenter
            }
        }
    }

//    NUCLEUS

    private val PRESENTER_STATE_KEY = "presenter_state"

    private val presenterDelegate = PresenterLifecycleDelegate(ReflectionPresenterFactory.fromViewClass<P>(javaClass))

    /**
     * Sets a presenter factory.
     * Call this method before onCreate/onFinishInflate to override default [ReflectionPresenterFactory] presenter factory.
     * Use this method for presenter dependency injection.
     */
    override fun setPresenterFactory(presenterFactory: PresenterFactory<P>?) {
        presenterDelegate.presenterFactory = presenterFactory
    }

    /**
     * Returns a current presenter factory.
     */
    override fun getPresenterFactory(): PresenterFactory<P>? {
        return presenterDelegate.presenterFactory
    }


    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onResume/onPause and onAttachedToWindow/onDetachedFromWindow calls
     * if the presenter factory returns a non-null value.
     *
     * @return a currently attached presenter or null.
     */
    override fun getPresenter(): P {
        return presenterDelegate.presenter
    }
}
