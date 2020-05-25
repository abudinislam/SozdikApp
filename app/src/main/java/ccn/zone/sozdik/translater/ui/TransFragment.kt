package ccn.zone.sozdik.translater.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ccn.zone.sozdik.R
import ccn.zone.sozdik.base.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_translater.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransFragment : BaseMvpFragment<TransContract.View, TransContract.Presenter>(),
    TransContract.View {
    companion object {
        fun create() = TransFragment()
    }

    private val presenterImpl: TransPresenter by viewModel()
    override val presenter: TransContract.Presenter
        get() = presenterImpl


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_translater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadTranslater()
        btn_transl.setOnClickListener {
            presenter.translates(editText.text.toString())
        }
    }
    override fun showFirstTranslater(text: String) {
        textViewRus.text = text
    }

    override fun showSecondTranslater(text: String) {
        textViewLat.text = text
    }


}