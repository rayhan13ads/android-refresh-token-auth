package com.example.refreshtokenapp.apiservice

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper( var context:Context) {

    companion object{

        val baseUrl = "http://admin.abookstore.co.ke/"
        var retrofit: Retrofit? = null

        //
        fun getClient(context: Context): Retrofit? {
            if (retrofit== null){
                retrofit= Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
    }

}