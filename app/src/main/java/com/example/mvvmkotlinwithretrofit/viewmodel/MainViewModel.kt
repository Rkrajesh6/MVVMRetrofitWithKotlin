package com.example.mvvmkotlinwithretrofit.viewmodel

import android.app.Application
import android.app.IntentService
import android.app.Service
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinwithretrofit.model.Heroes
import com.example.mvvmkotlinwithretrofit.network.OperationCallback
import com.example.mvvmkotlinwithretrofit.repository.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    //private val mutableList = MutableLiveData<List<ResponseClass.DataItem>>().apply { value = emptyList() }
    private val mutableList = MutableLiveData<List<Heroes>>().apply { value = emptyList() }
    //val listData : LiveData<List<ResponseClass.DataItem>> = mutableList
    val listData: LiveData<List<Heroes>> = mutableList
    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    /**
     * Created by : rajeshkumar07
     * Created Date/Time : 03-01-2020 17:22
     * Description : This method is used to load the hero API data.
     */
    fun loadData() {
        val repository = MainRepository()
        _isViewLoading.postValue(true)
        repository.retrieveResponse(object : OperationCallback {
            override fun onSuccess(obj: Any?) {
                _isViewLoading.postValue(false)
                if (obj != null && obj is List<*>) {
                    if (obj.isEmpty())
                        _isEmptyList.postValue(true)
                    else
                        mutableList.value = obj as List<Heroes>
                }
            }

            override fun onError(obj: Any?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(obj)
            }

        })
    }
}