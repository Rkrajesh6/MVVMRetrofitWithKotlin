package com.example.mvvmkotlinwithretrofit.network

interface OperationCallback {
    fun onSuccess(obj:Any?)
    fun onError(obj:Any?)
}