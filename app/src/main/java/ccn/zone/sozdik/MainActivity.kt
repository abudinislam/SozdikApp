package ccn.zone.sozdik

import android.os.Bundle
import ccn.zone.sozdik.R
import ccn.zone.sozdik.base.BaseActivity
import ccn.zone.sozdik.contract.AppRouterContract
import ccn.zone.sozdik.contract.MainActivityContract
import ccn.zone.sozdik.translit.ui.TranslaterFragment
import ccn.zone.sozdik.translit.ui.TranslaterPresenter
import com.facebook.stetho.Stetho
import org.koin.androidx.viewmodel.ext.android.viewModel
import ccn.zone.sozdik.TranslaterModule.create

class MainActivity : BaseActivity(), MainActivityContract.View, AppRouterContract {

    private val presenter: TranslaterPresenter by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showSomeView()

        Stetho.initializeWithDefaults(this)
    }

    override fun showSomeView() {
        replaceFragment(TranslaterFragment.create())
    }

    override fun showTranslit() {
    }

    override fun showTranslator() {
    }


    /*replaceFragment(TransFragment.create())

         */
}


