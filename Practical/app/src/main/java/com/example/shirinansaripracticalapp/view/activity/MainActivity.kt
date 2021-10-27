package com.example.shirinansaripracticalapp.view.activity

import android.os.Bundle
import com.example.shirinansaripracticalapp.R
import com.example.shirinansaripracticalapp.databinding.ActivityMainBinding
import com.example.shirinansaripracticalapp.view.activity.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}