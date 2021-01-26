package com.huyduc1108.imaginato.base

import android.content.BroadcastReceiver
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.huyduc1108.imaginato.utils.showLoadingAlertDialog
import com.huyduc1108.imaginato.utils.showNotesAlertDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


abstract class BaseActivity<T : ViewDataBinding?, V : ViewModel?> :
    AppCompatActivity(),
    HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    var binding: T? = null
    var viewModel: V? = null

    private lateinit var mReceiver: BroadcastReceiver
    private var networkDialog: AlertDialog? = null
    private var loadingDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView<T>(this, layoutId())
        viewModel = viewModelClass()?.let { ViewModelProviders.of(this, viewModelFactory)[it] }
        init()
    }

    protected abstract fun layoutId(): Int

    protected abstract fun viewModelClass(): Class<V>?

    protected abstract fun init()

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    fun showDialogNetwork() {
        if (networkDialog == null) {
            networkDialog = showNotesAlertDialog {
                cancelable = false
            }
        }

        if (networkDialog != null && !networkDialog?.isShowing!!) {
            networkDialog?.show()
        }
    }

    fun hiddenDialogNetwork() {
        if (networkDialog != null && networkDialog?.isShowing!!) {
            networkDialog?.hide()
        }
    }

    fun showDialogLoading() {
        if (loadingDialog == null) {
            loadingDialog = showLoadingAlertDialog {
                cancelable = false
            }
        }
        loadingDialog?.show()
    }

    fun hiddenDialogLoading() {
        if (loadingDialog == null) {
            return
        }
        loadingDialog?.dismiss()
    }
}