package com.nexmo.example.contactcenterstarter

import android.os.Bundle
import android.util.Log
import android.view.View
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoCallEventListener
import com.nexmo.client.getstarted.calls.BaseActivity
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener

class OnCallActivity : BaseActivity() {

    internal var callEventListener: NexmoCallEventListener = FinishOnCallEnd(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_call)
    }

    override fun onStart() {
        Log.d(TAG, "OnCallActivity - Start")
        super.onStart()
        currentCall?.addCallEventListener(callEventListener)
    }

    fun onHangup(view: View) {
        currentCall?.hangup(object : NexmoRequestListener<NexmoCall> {
            override fun onSuccess(call: NexmoCall?) {
                finish()
                currentCall = null
            }

            override fun onError(nexmoApiError: NexmoApiError) {
                notifyError(nexmoApiError)
            }

        })
    }


    override fun onStop() {
        Log.d(TAG, "OnCallActivity - Stop")
        currentCall?.removeCallEventListener(callEventListener)
        super.onStop()
    }

}
