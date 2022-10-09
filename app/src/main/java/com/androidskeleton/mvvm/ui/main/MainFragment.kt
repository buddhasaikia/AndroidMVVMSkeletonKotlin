package com.androidskeleton.mvvm.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.androidskeleton.mvvm.databinding.FragmentMainBinding
import com.androidskeleton.mvvm.di.scope.PerActivity
import com.androidskeleton.mvvm.ui.base.DaggerBaseFragment
import com.androidskeleton.mvvm.util.CustomViewModelFactory
import com.androidskeleton.mvvm.util.ErrorMessageFactory
import com.androidskeleton.mvvm.util.Utils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerActivity
class MainFragment : DaggerBaseFragment<FragmentMainBinding>() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    @Inject
    lateinit var errorMessageFactory: ErrorMessageFactory
    @Inject
    lateinit var utils: Utils
    private var mainViewModel: MainViewModel? = null

    override fun initUI() {
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        mainViewModel?.greetings()
            ?.subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    mainViewModel?.addSubscription(d)
                }

                override fun onNext(s: String) {
                    viewBinding.lblGreetings.text = s
                }

                override fun onError(e: Throwable) {
                    utils.showToast(errorMessageFactory.getError(e))
                }

                override fun onComplete() {}
            })
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding = FragmentMainBinding::inflate

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
