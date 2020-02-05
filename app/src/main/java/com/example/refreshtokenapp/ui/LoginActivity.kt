package com.example.refreshtokenapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.refreshtokenapp.R
import com.example.refreshtokenapp.models.Login
import com.example.refreshtokenapp.models.LoginResponse
import com.example.refreshtokenapp.repository.AuthRepository
import com.example.refreshtokenapp.utils.Sheard
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var passwrod: EditText
    lateinit var loginBtn:Button
    lateinit var registrationBtn:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initWidget()


        loginBtn.setOnClickListener({
                createLogin()
        })

        registrationBtn.setOnClickListener({
            goToRegisterPage()
        })
    }



    fun initWidget(){
        email = findViewById(R.id.login_emailId)
        passwrod = findViewById(R.id.login_passwordId)
        loginBtn = findViewById(R.id.LoginBtn)
        registrationBtn = findViewById(R.id.registerBtnId)
    }

    fun goToHome(loginRes:LoginResponse){

        var gson = Gson()
        var jsonData = gson.toJson(loginRes, LoginResponse::class.java)

        var intent = Intent(this,HomeActivity::class.java)
        intent.putExtra("login", jsonData)
        startActivity(intent)
    }

    fun createLogin(){
        var email = email.text.toString()
        var password = passwrod.text.toString()

        var login = Login(email = email, password = password,refreshToken = "")

        val auth = AuthRepository(this)
        auth.login(login,{
            Toast.makeText(this,"Success ${it.message}",Toast.LENGTH_LONG)

            if (it.success){
                var token = it.data.token
                var refreshToken = it.data.refreshToken
                Sheard.getInstance(this.getSharedPreferences("auth",Context.MODE_PRIVATE)).addSearedPerf(token,refreshToken)
                goToHome(it)
            }
        })
    }


//    fun addSearedPerf(token:String, refreshToken:String){
//        var searedPerf = getPreferences(Context.MODE_PRIVATE)
//        val editor = searedPerf.edit()
//        editor.putString("token",token)
//        editor.putString("refresh_token",refreshToken)
//        editor.apply()
//    }

    fun goToRegisterPage(){
        var intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}
