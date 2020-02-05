package com.example.refreshtokenapp.apiservice

import com.example.refreshtokenapp.models.Login
import com.example.refreshtokenapp.models.LoginResponse
import com.example.refreshtokenapp.models.Registration
import com.example.refreshtokenapp.models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface apiLisener {


    @POST("api/auth/login")
    fun createLogin(@Body params:Login):Call<LoginResponse>

    @POST("api/auth/register")
    fun createRegistration(@Body params: Registration): Call<RegistrationResponse>


}