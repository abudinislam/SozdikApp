package ccn.zone.sozdik.translater.ui

import android.annotation.SuppressLint
import android.util.Log
import ccn.zone.sozdik.base.BasePresenter
import ccn.zone.sozdik.translater.interactor.TransInteractor
import ccn.zone.sozdik.translater.model.Translater
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TransPresenter (
    private val transInteractor: TransInteractor
) : BasePresenter<TransContract.View>(),
    TransContract.Presenter {

    private var translaterList: MutableList<Translater> = mutableListOf()

    @SuppressLint("CheckResult")
    override fun loadTranslater() {
        transInteractor.getTrans()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    translaterList.addAll(it)
                    Log.d("Transtale", it.toString())
                },
                {
                    Log.d("Error", it.message ?: "Error")
                }
            )
    }

    override fun translates(text: String) {
        translaterList.forEach{
            if(text == it.ru) {
                view?.showFirstTranslater("Английский: " + it.en)
                view?.showSecondTranslater("Казахский: " + it.kz)
            }
            if (text == it.en){
                view?.showFirstTranslater("Русский: " + it.ru)
                view?.showSecondTranslater("Казахский: " + it.kz)
            }
            if (text == it.kz){
                view?.showFirstTranslater("Английский: " + it.en)
                view?.showSecondTranslater("Русский: " + it.ru)
            }
        }
    }


}