package com.example.refreshtokenapp.repository

import android.content.Context
import android.util.Log
import com.example.refreshtokenapp.apiservice.ApiHelper
import com.example.refreshtokenapp.apiservice.apiLisener
import com.example.refreshtokenapp.models.Login
import com.example.refreshtokenapp.models.LoginResponse
import com.example.refreshtokenapp.models.Registration
import com.example.refreshtokenapp.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(var context: Context) {

    companion object{
        val TAG = AuthRepository.javaClass.simpleName
    }

    fun login(loginModel:Login, res:(LoginResponse) -> Unit){

        val apiclient = ApiHelper.getClient(context)!!.create(apiLisener::class.java)

        if (loginModel != null){
            var apical = apiclient.createLogin(loginModel)
            apical.enqueue(object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e(TAG, "error : ${t.message}")
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful && response.code() == 200){
                        res(response.body()!!)
                    }

                }

            })
        }else{
            Log.e(TAG, "Login model null")
        }

    }


    fun registration(resgirationModel:Registration, res:(RegistrationResponse)-> Unit){

        val apiclient = ApiHelper.getClient(context)!!.create(apiLisener::class.java)

        if (resgirationModel != null){
            val apical = apiclient.createRegistration(resgirationModel)
            apical.enqueue(object: Callback<RegistrationResponse>{
                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Log.e(TAG, "Error: ${t.message}")
                }

                override fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {

                    if (response.isSuccessful && response.code() == 201){
                        res(response.body()!!)
                    }else{
                        Log.e(TAG,"${response.code()}")
                    }

                }

            })
        }else{
            Log.e(TAG, "Registration model null")
        }

    }



}