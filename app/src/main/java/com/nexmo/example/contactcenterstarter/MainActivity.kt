package com.nexmo.example.contactcenterstarter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoCallHandler
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoIncomingCallListener
import com.nexmo.client.getstarted.calls.*
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

class MainActivity : BaseActivity() {

    val incomingCallListener = NexmoIncomingCallListener { call ->
        Log.d(TAG, "NexmoIncomingCallListener.onIncomingCall()")
        currentCall = call
        startActivity(Intent(this@MainActivity, IncomingCallActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onStart() {
        super.onStart()
        NexmoClient.get().addIncomingCallListener(incomingCallListener)
        title = "Hi , ${currentUser?.name} !"
    }

    fun onPhoneCallClick(view: View) {
        startActivity(Intent(this@MainActivity, CallPhoneActivity::class.java))
    }

    override fun onStop() {
        NexmoClient.get().removeIncomingCallListeners()
        super.onStop()
    }

}
