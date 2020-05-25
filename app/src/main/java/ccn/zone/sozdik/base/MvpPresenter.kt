package ccn.zone.sozdik.base

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)

    fun detach()
}