package ccn.zone.sozdik.translit.ui


import ccn.zone.sozdik.base.MvpPresenter
import ccn.zone.sozdik.base.MvpView
import io.reactivex.Single
import ccn.zone.sozdik.entity.Words

interface TranslaterContract {
    interface View : MvpView {
        fun showLat(text: String)
        fun showKol(text: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun translate(text: String)
        fun kol(text: String)
        fun addInNewAd(wordsList: Words)
        fun getDate(): Single<MutableList<Words>?>?
        fun deleteDateDB(StorageList: Words)
    }
}