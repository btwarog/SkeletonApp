package pl.btwarog.skeletonapp.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import icepick.Icepick
import nucleus5.factory.PresenterFactory
import nucleus5.factory.ReflectionPresenterFactory
import nucleus5.presenter.Presenter
import nucleus5.view.PresenterLifecycleDelegate
import nucleus5.view.ViewWithPresenter
import pl.btwarog.skeletonapp.injection.Injector

/**
 * Base for standard Fragments, injects arguments with FragmentArgs and save states with Icepick.
 * Connects with presenter.
 */
abstract class BaseFragment<P : Presenter<*>> : Fragment(), ViewWithPresenter<P> {

    /**
     * Provides layoutId of visible screen
     *
     * @return
     */
    @get:IntegerRes
    protected abstract val layoutId: Int

    override fun onCreate(bundle: Bundle?) {
        bundle?.let {
            presenterDelegate.onRestoreInstanceState(it.getBundle(PRESENTER_STATE_KEY))
            Icepick.restoreInstanceState(this, it)
        }
        setInjectingPresenterFactory()
        super.onCreate(bundle)
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initClickListeners()
    }

    /**
     * Should have implementation of current view initial setup
     */
    protected open fun initView(savedInstanceState: Bundle?) {
    }

    /**
     * Function responsible for initialization of all click listeners in current view
     */
    protected open fun initClickListeners() {
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
                context?.run {
                    (applicationContext as Injector).inject(presenter)
                }
                presenter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenterDelegate.onResume(this)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


//    NUCLEUS

    private val PRESENTER_STATE_KEY = "fragment_presenter_state"
    private val presenterDelegate = PresenterLifecycleDelegate(ReflectionPresenterFactory.fromViewClass<P>(javaClass))

    /**
     * Returns a current presenter factory.
     */
    override fun getPresenterFactory(): PresenterFactory<P>? {
        return presenterDelegate.presenterFactory
    }

    /**
     * Sets a presenter factory.
     * Call this method before onCreate/onFinishInflate to override default [ReflectionPresenterFactory] presenter factory.
     * Use this method for presenter dependency injection.
     */
    override fun setPresenterFactory(presenterFactory: PresenterFactory<P>) {
        presenterDelegate.presenterFactory = presenterFactory
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

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        bundle.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState())
        Icepick.saveInstanceState(this, bundle)
    }

    override fun onPause() {
        super.onPause()
        presenterDelegate.onDropView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterDelegate.onDestroy(!activity!!.isChangingConfigurations)
    }

}
