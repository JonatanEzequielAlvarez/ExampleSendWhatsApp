package com.creardev.examplesendwhatsapp


import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiService {
    fun sendMessage(body: String, onResult: (message) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.sendMessage(body).enqueue(
            object : Callback<message> {
                override fun onFailure(call: Call<message>, t: Throwable) {
                    Log.e("Error", "not found")
                }
                override fun onResponse( call: Call<message>, response: Response<message>) {
                    val sendMessage = response.body()
                    onResult(sendMessage!!)
                }
            }
        )

    }

    fun addUser(userData: message, onResult: (message?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.addUser(userData).enqueue(
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