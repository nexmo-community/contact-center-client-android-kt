package com.nexmo.example.contactcenterstarter.network

import com.squareup.moshi.Json

data class UserTokenResponse(@Json(name = "user_name") val username: String, val jwt: String)

data class UserTokenRequest(val user_name: String, val mobile_api_key: String = getMobileApiKey())
