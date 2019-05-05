package com.nexmo.example.contactcenterstarter.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


var moshi = Moshi.Builder().build()

val YOUR_BASE_URL = "https://britt-test-rails.herokuapp.com/"
val YOUR_MOBILE_API_KEY = "1b47919fdcb9511eafbe4f35ed4a61c53c2144edd6a215d80a1a2b359b703312"

val httpLogging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
val httpClient = OkHttpClient.Builder().addInterceptor(httpLogging).build()

var retrofit = Retrofit.Builder()
    .baseUrl(YOUR_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(httpClient)
    .build()

var myApiService = retrofit.create<MyApiService>(MyApiService::class.java)