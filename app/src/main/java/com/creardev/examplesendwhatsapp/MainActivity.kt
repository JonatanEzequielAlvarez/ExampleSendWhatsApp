package com.creardev.examplesendwhatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.creardev.examplesendwhatsapp.databinding.ActivityMainBinding



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
        binding.refresh.isRefreshing = true
        var sitePhoneNumbrer = binding.edSitePhone.text
        var sitePhoneNumbrerFinish = sitePhoneNumbrer.toString().replace("+", "", false)
        val phone = (sitePhoneNumbrerFinish + binding.edPhone.text)
        val apiService = RestApiService()
        val data = message(
            phone,
            "3.550,55",
            "345",
            "Point Smart",
            "10",
            ""
        )

        apiService.sendMessage(data)
        {
            if (it?.status != null) {
                binding.refresh.isRefreshing = false
                if (it.status == "200") {
                    Toast.makeText(this@MainActivity, "Mensaje enviado", Toast.LENGTH_LONG).show()
                    Log.d("SUCCES", "Mensaje enviado")
                } else {
                    Toast.makeText(this@MainActivity, "Mensaje no enviado", Toast.LENGTH_LONG)
                        .show()
                    Log.d("ERROR", "Mensaje no enviado")
                }
            } else {
                binding.refresh.isRefreshing = false
                Toast.makeText(this@MainActivity, "Error de servidor", Toast.LENGTH_LONG).show()
                Log.d("ERROR_API", "Error registering new user")
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnSendMessage -> {
                sendMessage()
            }
        }
    }


}