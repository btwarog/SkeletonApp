package pl.btwarog.skeletonapp.injection.components

import pl.btwarog.skeletonapp.ui.home.HomePresenter
import pl.btwarog.skeletonapp.ui.home.homeOne.HomeOnePresenter

/**
 * Created by bartlomiej.twarog on 2018-06-20
 */
interface BaseApplicationComponentInjector {
    fun inject(presenter: HomePresenter)
    fun inject(presenter: HomeOnePresenter)
}