package com.nexmo.example.contactcenterstarter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoUser
import com.nexmo.client.getstarted.calls.*
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.example.contactcenterstarter.network.UserTokenRequest
import com.nexmo.example.contactcenterstarter.network.UserTokenResponse
import com.nexmo.example.contactcenterstarter.network.myApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init(applicationContext)
    }

    fun onLoginJaneClick(view: View) {
        Toast.makeText(baseContext, "click login as jane!", Toast.LENGTH_LONG).show()
        loginToSdk(USER_NAME_JANE)
    }


//    fun onLoginJoeClick(view: View) {
//        loginToSdk(USER_NAME_JOE)
//    }

    private fun loginToSdk(username: String) {
        myApiService.getUserToken(UserTokenRequest(username)).enqueue(object : Callback<UserTokenResponse> {
            override fun onResponse(call: Call<UserTokenResponse>, response: Response<UserTokenResponse>) {
                val token = response.body()?.jwt
                token?.let { login(it) }
            }

            override fun onFailure(call: Call<UserTokenResponse>, t: Throwable) {
                Toast.makeText(baseContext, "onFailure!", Toast.LENGTH_LONG).show()
            }
        })


    }

    private fun login(token: String) {
        NexmoClient.get().login(token, object : NexmoRequestListener<NexmoUser> {

            override fun onError(nexmoApiError: NexmoApiError) {
                notifyError(nexmoApiError)
            }

            override fun onSuccess(user: NexmoUser) {
                currentUser = user

                startActivity(Intent(baseContext, MainActivity::class.java))
                finish()
            }
        })
    }
}
