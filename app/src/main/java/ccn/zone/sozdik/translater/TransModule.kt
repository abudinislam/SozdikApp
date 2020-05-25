package ccn.zone.sozdik.translater

import androidx.room.Room
import ccn.zone.sozdik.base.InjectionModule
import ccn.zone.sozdik.dataBase.AppDatabase
import ccn.zone.sozdik.translater.api.TransApi
import ccn.zone.sozdik.translater.interactor.TransInteractor
import ccn.zone.sozdik.translater.ui.TransPresenter
import ccn.zone.sozdik.translit.api.TranslaterApi
import ccn.zone.sozdik.translit.ui.TranslaterPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object TransModule : InjectionModule {
    override fun create() = module {
        single { TransApi.create() }
        single { TransInteractor(get()) }
        viewModel { TransPresenter(get()) }
        viewModel { TranslaterPresenter(get()) }
        single { Room.databaseBuilder(get(), AppDatabase::class.java, "myDB").build() }
        single { get<AppDatabase>().myDao() }
        single { TranslaterApi(get()) }
    }
}