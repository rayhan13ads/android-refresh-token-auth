package com.example.refreshtokenapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.refreshtokenapp.R
import com.example.refreshtokenapp.models.LoginResponse
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {


    lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initWidget()
        getDataFormLogin()
    }

    fun initWidget(){
        result = findViewById(R.id.result_id)
    }

    fun getDataFormLogin(){

        var jsonData = intent.getStringExtra("login")
        var gson = Gson()
        var loginData = gson.fromJson<LoginResponse>(jsonData,LoginResponse::class.java)

        result.text = "${loginData.message} \n refresh token ${loginData.data.refreshToken} \n token ${loginData.data.token}"
    }
}
