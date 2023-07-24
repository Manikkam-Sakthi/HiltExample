package com.example.sampleapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.models.UserResponse
import com.example.sampleapp.repository.MainRepository
import com.example.sampleapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _result = MutableLiveData<Resource<UserResponse>>()
    val result: LiveData<Resource<UserResponse>> get() = _result

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        _result.postValue(Resource.loading())
        mainRepository.getUser().let {
            if (it.isSuccessful) {
                _result.postValue(Resource.success(data = it.body()))
            } else {
                _result.postValue(Resource.error(msg = it.errorBody().toString()))
            }
        }
    }

}