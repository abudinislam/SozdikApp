package ccn.zone.sozdik.translater.ui

import ccn.zone.sozdik.base.MvpPresenter
import ccn.zone.sozdik.base.MvpView


interface TransContract {
    interface View : MvpView {
        fun showFirstTranslater(text: String)
        fun showSecondTranslater(text: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadTranslater()
        fun translates(text: String)
    }
}