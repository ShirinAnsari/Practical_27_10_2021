package com.example.shirinansaripracticalapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.shirinansaripracticalapp.R
import com.example.shirinansaripracticalapp.databinding.FragmentWelcomeBinding
import com.example.shirinansaripracticalapp.view.fragment.base.BaseFragment

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getContentView(inflater, container, R.layout.fragment_welcome)
        return dataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding!!.btnStart.setOnClickListener {
            Navigation.findNavController(it).navigate(
                R.id.action_welcomeFragment_to_quizFragment
            )
        }

    }

}