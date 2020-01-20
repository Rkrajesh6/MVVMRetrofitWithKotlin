package com.example.mvvmkotlinwithretrofit.network

import com.example.mvvmkotlinwithretrofit.model.Heroes
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    //@GET("/api/museums/")
    //fun getData(): Call<ResponseClass>

    @GET("marvel")
    fun getHeroes(): Call<List<Heroes>>
}