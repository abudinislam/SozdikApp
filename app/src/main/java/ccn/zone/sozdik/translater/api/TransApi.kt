package ccn.zone.sozdik.translater.api

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ccn.zone.sozdik.translater.model.Translater

interface TransApi {
    companion object Factory {
        fun create(): TransApi {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://simplechat2345.herokuapp.com/")
                .build()

            return retrofit.create(TransApi::class.java);
        }
    }

    @GET("index.json")
    fun get(): Single<List<Translater>>
}
