package com.example.shirinansaripracticalapp.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.shirinansaripracticalapp.R
import com.example.shirinansaripracticalapp.databinding.FragmentQuizBinding
import com.example.shirinansaripracticalapp.model.Question
import com.example.shirinansaripracticalapp.view.fragment.base.BaseFragment
import com.example.shirinansaripracticalapp.viewmodel.QuizViewModel
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*
import kotlin.collections.ArrayList

class QuizFragment : BaseFragment<FragmentQuizBinding>(), View.OnClickListener {

    private var totalQue = 2
    private var queNo = 1

    private val min = 0
    private val max = 9
    private var locationOfRightAnswer = 0

    private var questionList = ArrayList<Question>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getContentView(inflater, container, R.layout.fragment_quiz)
        return dataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding!!.quizViewModel = QuizViewModel()

        //Header
        tvTitle.text = getString(R.string.str_questions)

        dataBinding!!.tvOptionOne.setOnClickListener(this)
        dataBinding!!.tvOptionTwo.setOnClickListener(this)
        dataBinding!!.tvOptionThree.setOnClickListener(this)
        dataBinding!!.tvOptionFour.setOnClickListener(this)

        setNextQueUI()
    }

    private fun setNextQueUI() {
        if (queNo > totalQue) {// All Questions done, move to result screen
            val bundle = Bundle()
            bundle.putParcelableArrayList(getString(R.string.str_questions), questionList)
            Navigation.findNavController(dataBinding!!.clMainQuiz).navigate(
                R.id.action_quizFragment_to_resultFragment, bundle
            )
        } else {//Next Question

            dataBinding!!.tvOptionOne.isSelected = false
            dataBinding!!.tvOptionTwo.isSelected = false
            dataBinding!!.tvOptionThree.isSelected = false
            dataBinding!!.tvOptionFour.isSelected = false

            dataBinding!!.tvQuestionNo.text = "Q.$queNo"

            val random = Random()
            val answers = ArrayList<Double>()
            //Question random digits
            val randomOne: Int = random.nextInt(max - min + 1) + min
            val randomTwo: Int = random.nextInt(max - min + 1) + min

            //Question random operator
            val operators = arrayOf("+", "-", "x", "/")
            val oprIndex: Int = random.nextInt(4)

            //Correct Answer
            val correctAnswer = dataBinding!!.quizViewModel!!.getCorrectAnswer(
                randomOne,
                randomTwo,
                operators[oprIndex]
            )

            //Adding Questions number and its answers to a list
            questionList.add(Question(queNo, correctAnswer.toString(), false))

            dataBinding!!.tvDigitOne.text = randomOne.toString()
            dataBinding!!.tvDigitTwo.text = randomTwo.toString()
            dataBinding!!.tvOperator.text = operators[oprIndex]

            locationOfRightAnswer = random.nextInt(4)
            var incorrectAnswer: Int
            answers.clear()

            //Generating Random options
            for (i in 0..3) {
                if (i == locationOfRightAnswer) {
                    answers.add(correctAnswer)
                } else {
                    incorrectAnswer = random.nextInt(12)
                    answers.add(incorrectAnswer.toDouble())
                }
            }

            dataBinding!!.tvOptionOne.text = answers[0].toString()
            dataBinding!!.tvOptionTwo.text = answers[1].toString()
            dataBinding!!.tvOptionThree.text = answers[2].toString()
            dataBinding!!.tvOptionFour.text = answers[3].toString()

            setCountDownTimer()
        }
    }

    private fun setCountDownTimer() {
        var sec = 30
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                dataBinding!!.tvSec.text = "0:" + dataBinding!!.quizViewModel!!.checkDigit(sec)
                sec--
            }

            override fun onFinish() {
                queNo++
                setNextQueUI()
            }
        }.start()
    }

    private fun setIsCorrectVal(position: Int) {//Settings value in list if user selects the right answer
        if (locationOfRightAnswer == position && questionList.size >= queNo) {
            questionList[queNo - 1].isCorrect = true
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.tvOptionOne -> {
                    dataBinding!!.tvOptionOne.isSelected = true
                    dataBinding!!.tvOptionTwo.isSelected = false
                    dataBinding!!.tvOptionThree.isSelected = false
                    dataBinding!!.tvOptionFour.isSelected = false

                    setIsCorrectVal(0)
                }
                R.id.tvOptionTwo -> {
                    dataBinding!!.tvOptionOne.isSelected = false
                    dataBinding!!.tvOptionTwo.isSelected = true
                    dataBinding!!.tvOptionThree.isSelected = false
                    dataBinding!!.tvOptionFour.isSelected = false

                    setIsCorrectVal(1)
                }
                R.id.tvOptionThree -> {
                    dataBinding!!.tvOptionOne.isSelected = false
                    dataBinding!!.tvOptionTwo.isSelected = false
                    dataBinding!!.tvOptionThree.isSelected = true
                    dataBinding!!.tvOptionFour.isSelected = false

                    setIsCorrectVal(2)
                }
                R.id.tvOptionFour -> {
                    dataBinding!!.tvOptionOne.isSelected = false
                    dataBinding!!.tvOptionTwo.isSelected = false
                    dataBinding!!.tvOptionThree.isSelected = false
                    dataBinding!!.tvOptionFour.isSelected = true

                    setIsCorrectVal(3)
                }
            }
        }
    }
}