package ccn.zone.sozdik.translit.ui

import android.annotation.SuppressLint
import android.util.Log
import ccn.zone.sozdik.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ccn.zone.sozdik.entity.Words
import ccn.zone.sozdik.translit.api.TranslaterApi

class TranslaterPresenter(private val trApi: TranslaterApi) : BasePresenter<TranslaterContract.View>(),
    TranslaterContract.Presenter {
    @SuppressLint("CheckResult")
    override fun translate(text: String) {
        Observable
            .fromCallable {
                val kir: List<String> = listOf(
                    "А", "Ә", "Б", "В", "Г", "Ғ", "Д", "Е", "Ж", "З",
                    "И", "Й", "К", "Қ", "Л", "М", "Н", "Ң", "О", "Ө", "П",
                    "Р", "С", "Т", "У", "Ұ", "Ү", "Ф", "Х", "Һ", "Ч", "Ш", "Ы", "І",
                    "а", "ә", "б", "в", "г", "ғ", "д", "е", "ж", "з",
                    "и", "й", "к", "қ", "л", "м", "н", "ң", "о", "ө", "п",
                    "р", "с", "т", "у", "ұ", "ү", "ф", "х", "һ", "ч", "ш", "ы", "і"
                )
                val lat: List<String> = listOf(
                    "A", "Á", "B", "V", "G", "Ǵ", "D", "E", "J", "Z",
                    "I", "I", "K", "Q", "L", "M", "N", "Ń", "O", "Ó", "P",
                    "R", "S", "T", "Ý", "U", "Ú", "F", "H", "H", "Ch", "Sh", "Y", "I",
                    "a", "á", "b", "v", "g", "ǵ", "d", "e", "j", "z",
                    "ı", "ı", "k", "q", "l", "m", "n", "ń", "o", "ó", "p",
                    "r", "s", "t", "ý", "u", "ú", "f", "h", "h", "ch", "sh", "y", "i"
                )
                var StrLat = ""
                text.forEach {
                    if (kir.contains(it.toString())) {
                        StrLat += lat[kir.indexOf(it.toString())]
                    } else {
                        StrLat += it
                    }
                }
                StrLat
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("Lat", it)
                    view?.showLat(it)
                },
                {
                    Log.d("Error", it.message)
                }
            )

    }

    @SuppressLint("CheckResult")
    override fun kol(text: String) {
        Observable
            .fromCallable {
                var kRes:String = ""
                val kir: List<String> = listOf(
                    "А", "Ә", "Б", "В", "Г", "Ғ", "Д", "Е", "Ж", "З",
                    "И", "Й", "К", "Қ", "Л", "М", "Н", "Ң", "О", "Ө", "П",
                    "Р", "С", "Т", "У", "Ұ", "Ү", "Ф", "Х", "Һ", "Ч", "Ш", "Ы", "І",
                    "а", "ә", "б", "в", "г", "ғ", "д", "е", "ж", "з",
                    "и", "й", "к", "қ", "л", "м", "н", "ң", "о", "ө", "п",
                    "р", "с", "т", "у", "ұ", "ү", "ф", "х", "һ", "ч", "ш", "ы", "і"
                )
                kir.forEachIndexed{index: Int, s: String ->
                    var Counts = 0
                    text.forEach { it:Char ->
                        if(s==it.toString()) {
                            Counts++
                        }
                    }
                    var res = Math.round(((Counts /text.length.toDouble())*100))
                    if (Counts !=0) {
                        kRes += "${kir.get(index)} - $res % \n"
                    }
                }
                kRes
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view?.showKol(it)
                }, {
                    Log.d("Error", it.message)
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun addInNewAd(wordsList: Words) {
        trApi.addToTable(wordsList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("almat", "1")
                },
                {
                    Log.d("almat","2")
                }
            )
    }

    override fun getDate(): Single<MutableList<Words>?>? {
        return null
    }

    override fun deleteDateDB(StorageList: Words) {
        TODO("Not yet implemented")
    }

    /*override fun getDate(): Single<MutableList<Words>?>? = trApi.getTableFromDB()
    override fun deleteDateDB(StorageList: Words) {
        trApi.deleteTableInDB(StorageList)
    }

     */


}