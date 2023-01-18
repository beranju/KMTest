package com.nextgen.kmtest.ui.thirdscreen

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nextgen.kmtest.data.UserRepository
import com.nextgen.kmtest.data.remote.response.DataItem
import com.nextgen.kmtest.di.viewModelModule
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

class ThirdViewModel(private val repository: UserRepository): ViewModel() {

    val  getUser: LiveData<PagingData<DataItem>> = repository.getUser().asLiveData().cachedIn(viewModelScope)
}