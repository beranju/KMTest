package com.nextgen.kmtest.ui.firstscreen

import androidx.lifecycle.ViewModel
import com.nextgen.kmtest.data.UserRepository

class FirstViewModel(private val repository: UserRepository): ViewModel() {

    fun saveUser(name: String) = repository.sesiManager.setUser(name)

}