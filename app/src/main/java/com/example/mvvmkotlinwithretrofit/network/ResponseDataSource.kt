package com.example.mvvmkotlinwithretrofit.network

interface ResponseDataSource {
    fun retrieveResponse(callback: OperationCallback)
    fun cancel()
}