package com.example.refreshtokenapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.refreshtokenapp.ui.HomeActivity
import com.example.refreshtokenapp.ui.LoginActivity
import com.example.refreshtokenapp.utils.Sheard

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({

            checkLogin()

        }, 3000)
    }


    fun checkLogin(){
        var token = Sheard.getInstance(this.getSharedPreferences("auth", Context.MODE_PRIVATE)).getToken()

        if (token!!.isEmpty()){
            goToLogin()
        }else{

            goToHome()
        }
    }

    fun goToLogin(){
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun goToHome(){
        var intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
