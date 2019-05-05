package com.nexmo.example.contactcenterstarter.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApiService {

    @POST("/api/jwt")
    fun getUserToken(@Body request: UserTokenRequest): Call<UserTokenResponse>
}

