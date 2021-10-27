package com.example.shirinansaripracticalapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var showAlertMessage = MutableLiveData<String>()

    fun setIsLoading(isStartLoading: Boolean) {
        isLoading.value = isStartLoading
    }
}