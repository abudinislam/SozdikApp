package ccn.zone.sozdik.translit.api

import ccn.zone.sozdik.dao.MyDao
import io.reactivex.Completable
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

    fun deleteTableInDB(StorageList: Words) {
        Completable.fromCallable { dao?.deleteTable(StorageList) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

     */


}