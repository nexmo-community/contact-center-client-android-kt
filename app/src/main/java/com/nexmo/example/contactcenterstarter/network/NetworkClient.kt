package com.nexmo.example.contactcenterstarter.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


var moshi = Moshi.Builder().build()

val httpLogging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
val httpClient = OkHttpClient.Builder().addInterceptor(httpLogging).build()

var retrofit = Retrofit.Builder()
    .baseUrl(getBaseUrl())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(httpClient)
    .build()

fun getBaseUrl(): String {
    TODO("swap with your server base url, for example: \"https://xxxxxx.herokuapp.com/\"")
}

fun getMobileApiKey(): String {
    TODO("swap with your mobile API key. Nexmo demo backend provides it on the SDK Integration page")
}

var myApiService = retrofit.create<MyApiService>(MyApiService::class.java)