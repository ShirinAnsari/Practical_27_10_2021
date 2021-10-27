package com.example.shirinansaripracticalapp.view.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected var dataBinding: B? = null
    private var rootView: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * This method used to Set layouts in the screens
     */
    protected fun getContentView(inflater: LayoutInflater, container: ViewGroup?, layoutId: Int) {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    }
}
