package com.creardev.examplesendwhatsapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8")
    @POST("testRetrofit.php")
    fun sendMessage(@Body data: message): Call<message>


}