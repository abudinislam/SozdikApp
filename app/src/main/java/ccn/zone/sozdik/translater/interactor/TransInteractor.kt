package ccn.zone.sozdik.translater.interactor

import ccn.zone.sozdik.translater.api.TransApi
import io.reactivex.Single
import ccn.zone.sozdik.translater.model.Translater

class TransInteractor (private val transApi: TransApi){
    fun getTrans():Single<List<Translater>> = transApi.get()
}