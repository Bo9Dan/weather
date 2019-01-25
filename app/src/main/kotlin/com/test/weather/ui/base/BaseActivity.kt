package com.test.weather.ui.base

import com.test.weather.R
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.test.weather.BR
import com.test.weather.injection.createFactory
import com.test.weather.injection.module.Injectable
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseActivityViewModel> : AppCompatActivity(), Injectable {
    lateinit var binding: VDB
    private var errorSnackBar: Snackbar? = null

    @Inject
    protected lateinit var viewModel: VM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
//        viewModel.setErrorClickListener()
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.activityToStart.observe(this, Observer { value ->
            val intent = Intent(this, value.first.java)
            if (value.second != null)
                intent.putExtras(value.second!!)
            startActivity(intent)
        })
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, getLayoutResource())
        val viewModelFactory = viewModel.createFactory()
        ViewModelProviders.of(this, viewModelFactory).get(viewModel.javaClass)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    abstract fun getLayoutResource(): Int

    fun showError(errorMessage: Any) {
        val error: String = if (errorMessage is String) errorMessage else binding.root.context.getString(errorMessage as Int)
        errorSnackBar = Snackbar.make(binding.root, error, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(com.test.weather.R.string.retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }


    fun hideError() {
        errorSnackBar?.dismiss()
    }

}