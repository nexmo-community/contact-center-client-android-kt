package com.nexmo.example.contactcenterstarter

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

class MainActivity : BaseActivity() {

    val incomingCallListener = NexmoIncomingCallListener { call ->
        Log.d(TAG, "NexmoIncomingCallListener.onIncomingCall()")
        currentCall = call
        startActivity(Intent(this@MainActivity, IncomingCallActivity::class.java))
    }

    var callListener: NexmoRequestListener<NexmoCall> = object : NexmoRequestListener<NexmoCall> {
        override fun onError(nexmoApiError: NexmoApiError) {
            notifyError(nexmoApiError)
        }

        override fun onSuccess(call: NexmoCall) {
            currentCall = call

            val intent = Intent(this@MainActivity, OnCallActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUiAccordingToEnabledFeatures()
    }


    override fun onStart() {
        Log.d(TAG, "MainActivity - Start")
        super.onStart()
        Log.d(TAG, "MainActivity - NexmoClient.get() == null ? " + (NexmoClient.get() == null))
        Log.d(TAG, "MainActivity - incomingCallListener == null ? " + (incomingCallListener == null))
        NexmoClient.get().addIncomingCallListener(incomingCallListener)
    }

//    fun onInAppCallClick(view: View) {
//        val callee = listOf(otherUserName)
//        NexmoClient.get().call(callee, NexmoCallHandler.IN_APP, callListener)
//    }

//    fun onPhoneCallClick(view: View) {
//        val callee = listOf(PLACEHOLDER) //TODO: swap with your phone number
//        NexmoClient.get().call(callee, NexmoCallHandler.SERVER, callListener)
//    }

    override fun onStop() {
        Log.d(TAG, "MainActivity - Stop")
        NexmoClient.get().removeIncomingCallListeners()
        super.onStop()
    }

    private fun setUiAccordingToEnabledFeatures() {
        tvTitle.text = "Hi , ${currentUser?.name} !"
    }
}
