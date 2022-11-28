package com.creardev.examplesendwhatsapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {
//text/html; charset=UTF-8
    //@Headers("Content-Type: application/json")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
    @POST("testRetrofit.php")
    fun addUser(@Body userData: message): Call<message>

    @Headers("Content-Type: text/html;charset=UTF-8")
    @POST("testRetrofit.php")
    fun sendMessage(@Body body: String): Call<message>


    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("testRetrofit.php")
    fun postData(@Body dataModal: message?): Call<message?>?


}