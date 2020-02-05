package com.example.refreshtokenapp.utils

import android.content.Context
import android.content.SharedPreferences

class Sheard (var prf:SharedPreferences){

    private var sharedPref: SharedPreferences? = null
    private var editor:SharedPreferences.Editor? = null


    companion object{

        @Volatile
        private var INSTANCE: Sheard? = null
        fun getInstance( prf:SharedPreferences) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Sheard(prf).also {
                    INSTANCE = it
                }
            }
    }


    init {
        this.sharedPref = prf
        this.editor = prf.edit()
    }

    fun addSearedPerf(token:String, refreshToken:String){
        editor!!.putString("token",token)
        editor!!.putString("refresh_token",refreshToken)
        editor!!.apply()
    }

    fun getToken():String?{
        return sharedPref!!.getString("token","")
    }

    fun getRefreshToken():String?{
        return sharedPref!!.getString("refresh_token","")
    }


}