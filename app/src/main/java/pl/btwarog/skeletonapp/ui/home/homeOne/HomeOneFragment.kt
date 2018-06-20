package pl.btwarog.skeletonapp.ui.home.homeOne

import android.annotation.SuppressLint
import android.os.Bundle
import kotlinx.android.synthetic.main.fragment_home_one.*
import nucleus5.factory.RequiresPresenter
import pl.btwarog.skeletonapp.R
import pl.btwarog.skeletonapp.domain.entities.GithubRepoModel
import pl.btwarog.skeletonapp.ui.base.BaseFragment
import timber.log.Timber

/**
 *  View for "HomeOne" tab on Home Screen
 */
@RequiresPresenter(HomeOnePresenter::class)
class HomeOneFragment : BaseFragment<HomeOnePresenter>(), HomeOneView {
    override fun onResultReceived(response: List<GithubRepoModel>?) {
        if(response == null || response.isEmpty()) {
            Timber.e("Empty answer")
            return
        }
        home_one_content.text = "Repositories sum: ${response.size}"
    }

    override val layoutId: Int
        @SuppressLint("ResourceType")
        get() = R.layout.fragment_home_one

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        presenter.fetchData()

    }

    override fun initClickListeners() {
        super.initClickListeners()
    }
}
