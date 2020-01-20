package com.example.mvvmkotlinwithretrofit.repository

import android.util.Log
import com.example.mvvmkotlinwithretrofit.model.Heroes
import com.example.mvvmkotlinwithretrofit.network.OperationCallback
import com.example.mvvmkotlinwithretrofit.network.ResponseDataSource
import com.example.mvvmkotlinwithretrofit.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository : ResponseDataSource {
    val TAG = "MainRepository"
    //private var call: Call<ResponseClass>? = null
    private var call: Call<List<Heroes>>? = null

    override fun retrieveResponse(callback: OperationCallback) {
        call = RetrofitService.build()?.getHeroes()
        call?.enqueue(object : Callback<List<Heroes>>{
            override fun onFailure(call: Call<List<Heroes>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<List<Heroes>>, response: Response<List<Heroes>>) {
                response.body()?.let {
                    if(response.isSuccessful){
                        Log.e(TAG, "message = ${it}")
                        callback.onSuccess(it)
                    }else{
                        callback.onError(it)
                    }
                }
            }

        })
    }

    override fun cancel() {
        call?.cancel()
    }




   /* override fun retrieveResponse(callback: OperationCallback) {
        call = RetrofitService.build()?.getData()
        call?.enqueue(object : Callback<ResponseClass> {
            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<ResponseClass>, response: Response<ResponseClass>) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.e(TAG, "message = ${it.data}")
                        callback.onSuccess(it.data)
                    } else {
                        callback.onError(it.msg)
                    }
                }
            }

        })
    }

    override fun cancel() {
        call?.cancel()
    }*/

}