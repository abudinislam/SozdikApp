package ccn.zone.sozdik.translit.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ccn.zone.sozdik.R
import ccn.zone.sozdik.base.BaseMvpFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ccn.zone.sozdik.entity.Words
import ccn.zone.sozdik.NewAdapter
import ccn.zone.sozdik.TranslaterAdapter
import ccn.zone.sozdik.translater.ui.TransFragment
import kotlinx.android.synthetic.main.fragment_translater.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslaterFragment : BaseMvpFragment<TranslaterContract.View, TranslaterContract.Presenter>(),
    TranslaterContract.View {

        companion object {


            fun create() = TranslaterFragment()

            private var myAdapter: TranslaterAdapter? = null
            private var newAdapter: NewAdapter? = null

            }
            private val presenterImpl: TranslaterPresenter by viewModel()
            override val presenter: TranslaterContract.Presenter
                get() = presenterImpl



        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_translater, container, false)

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

        btn_transl.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, TransFragment.create())
                ?.apply { addToBackStack(this::class.java.simpleName) }
                ?.commit()                                                         ///////////////////////
        }

        presenter.getDate()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    newAdapter?.addItems(it as List<Words>)
                },
                {
                    Log.e("s","s")
                }

            )

        btn_tr.setOnClickListener {
            presenter.translate(editText.text.toString())
            presenter.kol(editText.text.toString().toLowerCase())
            setRec()
        }

        myAdapter = TranslaterAdapter(
            avatarClickListener = { position: Int ->
                myAdapter?.delete(position)
            },
            favImgClickListener = { position: Int ->
                val getFromAdapter = myAdapter?.getItemByPosition(position)
                getFromAdapter?.let {
                    presenter.addInNewAd(it)
                }

                presenter.getDate()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                        {
                            newAdapter?.addItems(it as List<Words>)
                        },
                        {
                            Log.e("s", "s")
                        }

                    )


            }
        )
        newAdapter = NewAdapter(requireContext(),
            clickListener = { position: Int ->
                presenter.deleteDateDB(newAdapter!!.getItem(position))
                newAdapter?.delete(position)
            }
        )
        favRecycler.layoutManager = LinearLayoutManager(context)
        favRecycler.adapter = newAdapter


    }

    private fun setRec() {
        val manager = LinearLayoutManager(context)
        recyclerViewText.apply {
            layoutManager = manager
            adapter = myAdapter
        }
        myAdapter?.addItems(
            mutableListOf(
                Words(
                    textKir = editText.text.toString(),
                    textLat = textViewLat.text.toString()
                )
            )
        )
    }

    override fun showLat(text: String) {
        textViewLat.text = text
    }

    override fun showKol(text: String) {
        textViewFreq.text = text
    }
}