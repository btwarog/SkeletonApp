package pl.btwarog.skeletonapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import nucleus5.view.ViewWithPresenter
import pl.btwarog.skeletonapp.R
import pl.btwarog.skeletonapp.infrastructure.FragmentsContainer
import pl.btwarog.skeletonapp.infrastructure.navigator.FragmentNavigator
import pl.btwarog.skeletonapp.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<HomePresenter>(), FragmentsContainer, ViewWithPresenter<HomePresenter>, HomeView {

    override val screenTitle: String
        get() = "HOME"

    override val layoutId: Int
        @SuppressLint("ResourceType")
        get() = R.layout.activity_home

    override val fragmentContainerViewId: Int
        get() = R.id.fragment_container

    @Inject
    lateinit var fragmentNavigator: FragmentNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            fragmentNavigator.goToHomeOne()
        }

    }

}
