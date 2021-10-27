package com.example.shirinansaripracticalapp.view.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Parent activity for all activities
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    protected var dataBinding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    /**
     * This method used to Set layouts in the screens
     */
    protected fun getContentView(layoutId: Int) {
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
    }
}