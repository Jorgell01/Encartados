package com.example.encartados.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _welcomeMessage = MutableLiveData<String>().apply {
        value = "Welcome to the store, Enjoy your shopping!"
    }
    val welcomeMessage: LiveData<String> = _welcomeMessage

}