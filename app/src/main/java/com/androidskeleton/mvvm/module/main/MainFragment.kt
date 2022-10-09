package com.androidskeleton.mvvm.module.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.androidskeleton.mvvm.R
import com.androidskeleton.mvvm.di.scope.PerActivity
import com.androidskeleton.mvvm.module.base.DaggerBaseFragment
import com.androidskeleton.mvvm.util.CustomViewModelFactory
import com.androidskeleton.mvvm.util.ErrorMessageFactory
import com.androidskeleton.mvvm.util.Utils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerActivity
class MainFragment : DaggerBaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_main

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    @Inject
    lateinit var errorMessageFactory: ErrorMessageFactory
    @Inject
    lateinit var utils: Utils
    private var mainViewModel: MainViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        val lblGreetings = rootView?.findViewById<TextView>(R.id.lbl_greetings)
        mainViewModel?.greetings()
            ?.subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    mainViewModel?.addSubscription(d)
                }

                override fun onNext(s: String) {
                    lblGreetings?.text = s
                }

                override fun onError(e: Throwable) {
                    utils.showToast(errorMessageFactory.getError(e))
                }

                override fun onComplete() {}
            })
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
