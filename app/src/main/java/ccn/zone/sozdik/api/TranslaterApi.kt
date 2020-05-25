package ccn.zone.sozdik.api

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ccn.zone.sozdik.dao.MyDao
import ccn.zone.sozdik.entity.Words

class TranslaterApi(private val dao: MyDao?) {
    fun addToTable(wordsList: Words): Completable =
        Completable
            .fromCallable {
                dao?.insertTable(
                    Words(
                        textKir = wordsList.textKir,
                        textLat = wordsList.textLat
                    )
                )
            }

    /*fun getTableFromDB(): Single<MutableList<Words>?>? = Single.fromCallable { dao?.getTable() }

     */

    fun deleteTableInDB(StorageList: Words) {
        Completable.fromCallable { dao?.deleteTable(StorageList) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}