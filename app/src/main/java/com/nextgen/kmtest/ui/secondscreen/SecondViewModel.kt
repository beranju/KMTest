package com.nextgen.kmtest.ui.secondscreen

import androidx.lifecycle.ViewModel
import com.nextgen.kmtest.data.UserRepository

class SecondViewModel(private val repository: UserRepository): ViewModel() {
    val user = repository.sesiManager.getUser()
}