package com.creardev.examplesendwhatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.toolbox.HttpResponse
import com.creardev.examplesendwhatsapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnSendMessage.setOnClickListener(this)
        binding.edSitePhone.isEnabled = false

    }


    fun sendMessage() {
        var sitePhoneNumbrer = binding.edSitePhone.text
        var sitePhoneNumbrerFinish = sitePhoneNumbrer.toString().replace("+", "", false)
        val phone = (sitePhoneNumbrerFinish + binding.edPhone.text)
        val apiService = RestApiService()
        val userInfo = message(
            phone.toString(),
            "3.550,55",
            "345",
            "Point Smart",
            "10",
            ""
        )

        val json = Gson().toJson(userInfo)


        apiService.addUser(userInfo)
        {
            if (it?.status != null) {
                if (it.status == "200") {
                    Toast.makeText(this@MainActivity, "Mensaje enviado", Toast.LENGTH_LONG).show()
                    Log.d("SUCCES", "Mensaje enviado")
                } else {
                    Toast.makeText(this@MainActivity, "Mensaje no enviado", Toast.LENGTH_LONG)
                        .show()
                    Log.d("ERROR", "Mensaje no enviado")
                }
            } else {
                Toast.makeText(this@MainActivity, "Error de servidor", Toast.LENGTH_LONG).show()
                Log.d("ERROR_API", "Error registering new user")
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSendMessage -> {
                sendMessage()
                //postData()
            }
        }
    }

    private fun postData() {
        // below line is for displaying our progress bar.


        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://creardev.com.ar/turnos/")
            // as we are sending data in json format so
            // we have to add Gson converter factory
            .addConverterFactory(GsonConverterFactory.create())
            // at last we are building our retrofit builder.
            .build()
        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(RestApi::class.java)

        // passing data from our text fields to our modal class.
        val dataModal = message(
            "543573405377",
            "Ezequiel",
            "345",
            "lunes",
            "10",
            ""
        )
        val json = Gson().toJson(dataModal)
        // calling a method to create a post and passing our modal class.
        val call: Call<message?>? = retrofitAPI.postData(dataModal)

        // on below line we are executing our method.
        call!!.enqueue(object : Callback<message?> {
            override fun onResponse(call: Call<message?>?, response: Response<message?>) {
                // this method is called when we get response from our api.
                Toast.makeText(this@MainActivity, "Data added to API", Toast.LENGTH_SHORT).show()

                // below line is for hiding our progress bar.


                // on below line we are setting empty text
                // to our both edit text.


                // we are getting response from our body
                // and passing it to our modal class.
                val response: message? = response.body()

                // on below line we are getting our data from modal class
                // and adding it to our string.
                val responseString =
                    "Response Code : " + "201" + "\n" + "Name : " + response!!.nombre + "Job : " + response!!.dia

                // below line we are setting our
                // string to our text view.
                Toast.makeText(this@MainActivity, responseString, Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<message?>?, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}