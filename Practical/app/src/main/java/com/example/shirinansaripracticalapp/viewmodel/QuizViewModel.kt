package com.example.shirinansaripracticalapp.viewmodel

class QuizViewModel : BaseViewModel() {

    fun checkDigit(number: Int): String {
        return if (number <= 9) "0$number" else number.toString()
    }

    fun getCorrectAnswer(randomOne: Int, randomTwo: Int, operator: String): Double {
        var result = 0.0
        try {
            when (operator) {
                "/" -> {
                    result = (randomOne / randomTwo).toDouble()
                }
                "-" -> {
                    result = (randomOne - randomTwo).toDouble()
                }
                "*" -> {
                    result = (randomOne * randomTwo).toDouble()
                }
                "+" -> {
                    result = (randomOne + randomTwo).toDouble()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}