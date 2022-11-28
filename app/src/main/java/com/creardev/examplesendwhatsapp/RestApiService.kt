package com.creardev.examplesendwhatsapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiService {

    fun sendMessage(data: message, onResult: (message?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.sendMessage(data).enqueue(
            object : Callback<message> {
                override fun onFailure(call: Call<message>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<message>, response: Response<message>) {
                    val sendMessage = response.body()
                    onResult(sendMessage)
                }
            }
        )
    }

}