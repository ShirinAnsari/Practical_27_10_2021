package com.example.shirinansaripracticalapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shirinansaripracticalapp.R
import com.example.shirinansaripracticalapp.databinding.FragmentResultBinding
import com.example.shirinansaripracticalapp.model.Question
import com.example.shirinansaripracticalapp.view.adapter.ResultAdapter
import com.example.shirinansaripracticalapp.view.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.layout_toolbar.*

class ResultFragment : BaseFragment<FragmentResultBinding>(), View.OnClickListener {

    private var questionList = ArrayList<Question>()
    private var resultAdapter: ResultAdapter? = null
    private var marks = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getContentView(inflater, container, R.layout.fragment_result)
        return dataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundle()

        //Header
        tvTitle.text = getString(R.string.str_result)
        dataBinding!!.tvDone.setOnClickListener(this)

        setResultAdapter()
        marks = questionList.filter { it.isCorrect!! }.size
        dataBinding!!.tvMarks.text = marks.toString() + "/" + questionList.size
    }

    private fun setResultAdapter() {
        dataBinding!!.rvResult.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        resultAdapter = ResultAdapter(questionList)
        dataBinding!!.rvResult.adapter = resultAdapter
    }

    private fun getBundle() {
        val bundle = arguments
        questionList.addAll(bundle!!.getParcelableArrayList<Question>(getString(R.string.str_questions)) as ArrayList<Question>)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.tvDone -> {
                    Navigation.findNavController(dataBinding!!.tvDone).navigate(
                        R.id.action_resultFragment_to_welcomeFragment
                    )
                }
            }
        }
    }
}