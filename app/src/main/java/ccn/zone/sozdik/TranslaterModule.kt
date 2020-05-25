package ccn.zone.sozdik


import androidx.room.Room
import ccn.zone.sozdik.base.InjectionModule
import ccn.zone.sozdik.dataBase.AppDatabase
import ccn.zone.sozdik.translater.interactor.TransInteractor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ccn.zone.sozdik.translater.api.TransApi
import ccn.zone.sozdik.translater.ui.TransPresenter

object TranslaterModule: InjectionModule {
    override fun create() = module {
        single { Room.databaseBuilder(get(), AppDatabase::class.java, "myDB").build() }
        single { get<AppDatabase>().myDao() }
        single { TransApi.create() }
        single { TransInteractor(get()) }
        viewModel { TransPresenter(get()) }
    }
}