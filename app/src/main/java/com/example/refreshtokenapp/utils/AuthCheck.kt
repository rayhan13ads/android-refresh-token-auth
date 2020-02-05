package com.example.refreshtokenapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.auth0.android.jwt.JWT
import com.example.refreshtokenapp.models.Login
import com.example.refreshtokenapp.repository.AuthRepository
import com.google.gson.Gson

class AuthCheck(var context: Context) {

    var isExpired = false

    var token = Sheard.getInstance(context.getSharedPreferences("auth", Context.MODE_PRIVATE)).getToken()

    companion object{

        @Volatile
        private var INSTANCE: AuthCheck? = null
        fun getInstance( context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthCheck(context).also {
                    INSTANCE = it
                }
            }
    }


    fun decod(refreshToken:String,action:(Boolean)->Unit){

        if (token!!.isNotEmpty()){
            var jwt = JWT(token!!)
            isExpired = jwt.isExpired(10)
            if (isExpired){
                val auth = AuthRepository(context)
                val login = Login("","",refreshToken)
                auth.login(login,{
                    Sheard.getInstance(context.getSharedPreferences("auth",Context.MODE_PRIVATE)).addSearedPerf(it.data.token,it.data.refreshToken)
                    action(true)
                })
            }else{
                action(false)
            }
        }else{
            action(false)
        }


    }


}