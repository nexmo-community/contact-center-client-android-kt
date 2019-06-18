package com.nexmo.example.contactcenterstarter

import android.content.Context
import android.util.Log
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoUser
import com.nexmo.client.request_listener.NexmoLoginListener
import java.lang.ref.WeakReference

const val TAG = "contact-center"

const val USER_NAME_JANE = "Jane"
const val USER_NAME_JOE = "Joe"

var loginListener: NexmoLoginListener =
    NexmoLoginListener { status, reason -> Log.d(TAG, "NexmoLoginListener.onLoginStateChange : $status : $reason") }

var currentUser: NexmoUser? = null
var currentCall: NexmoCall? = null
lateinit var contextRef: WeakReference<Context>
private var didInit: Boolean = false

fun init(appContext: Context) {
    if (didInit) {
        return
    }
    didInit = true
    contextRef = WeakReference(appContext)
    NexmoClient.init(NexmoClient.NexmoClientConfig(), appContext, loginListener)
}
