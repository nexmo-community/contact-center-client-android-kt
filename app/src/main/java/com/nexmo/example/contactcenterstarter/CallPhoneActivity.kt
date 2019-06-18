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
import kotlinx.android.synthetic.main.activity_call_phone.*


class CallPhoneActivity : BaseActivity() {

    var callListener: NexmoRequestListener<NexmoCall> = object : NexmoRequestListener<NexmoCall> {
        override fun onError(nexmoApiError: NexmoApiError) {
            notifyError(nexmoApiError)
        }

        override fun onSuccess(call: NexmoCall) {
            currentCall = call

            val intent = Intent(this@CallPhoneActivity, OnCallActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_phone)

        dial1.setOnClickListener { tvPhoneNumber.text.append('1')}
        dial2.setOnClickListener { tvPhoneNumber.text.append('2')}
        dial3.setOnClickListener { tvPhoneNumber.text.append('3')}
        dial4.setOnClickListener { tvPhoneNumber.text.append('4')}
        dial5.setOnClickListener { tvPhoneNumber.text.append('5')}
        dial6.setOnClickListener { tvPhoneNumber.text.append('6')}
        dial7.setOnClickListener { tvPhoneNumber.text.append('7')}
        dial8.setOnClickListener { tvPhoneNumber.text.append('8')}
        dial9.setOnClickListener { tvPhoneNumber.text.append('9')}
        dial0.setOnClickListener { tvPhoneNumber.text.append('0')}
        dialHash.setOnClickListener { tvPhoneNumber.text.append('#')}
        dialStar.setOnClickListener { tvPhoneNumber.text.append('*')}
    }


    override fun onStart() {
        super.onStart()
        title = "Hi , ${currentUser?.name} !"
    }


    fun onCallPhoneClick(v: View) {
        val callee = listOf(tvPhoneNumber.text.toString())
        NexmoClient.get().call(callee, NexmoCallHandler.SERVER, callListener)
    }

    fun onErase(view: View) {
        if(tvPhoneNumber.text.isNotEmpty()) {
            tvPhoneNumber.text.delete(tvPhoneNumber.text.length-1, tvPhoneNumber.text.length)
        }
    }

}
