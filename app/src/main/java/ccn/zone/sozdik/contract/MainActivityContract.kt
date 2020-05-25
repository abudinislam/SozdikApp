package ccn.zone.sozdik.contract

import ccn.zone.sozdik.base.MvpPresenter
import ccn.zone.sozdik.base.MvpView

interface MainActivityContract {
    interface View : MvpView {
        fun showSomeView()
    }

    interface Presenter : MvpPresenter<View> {
        fun load()
    }
}