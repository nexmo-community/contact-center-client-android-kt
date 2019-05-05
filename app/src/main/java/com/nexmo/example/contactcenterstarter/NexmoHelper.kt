package com.nexmo.example.contactcenterstarter

import android.content.Context
import android.util.Log
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoConnectionState
import com.nexmo.client.NexmoUser
import com.nexmo.client.request_listener.NexmoLoginListener
import java.lang.ref.WeakReference

const val PLACEHOLDER = "PLACEHOLDER"
const val TAG = "contact-center"

const val USER_NAME_JANE = "Jane"
const val USER_NAME_JOE = "Joe"
const val JWT_JANE = PLACEHOLDER //TODO: swap with the JWT you generated for Jane
const val JWT_JOE = PLACEHOLDER //TODO: swap with the JWT you generated for Joe

var loginListener: NexmoLoginListener = object : NexmoLoginListener {
    override fun onLoginStateChange(eLoginState: NexmoLoginListener.ELoginState, eLoginStateReason: NexmoLoginListener.ELoginStateReason) {
        Log.d(TAG, "NexmoLoginListener.onLoginStateChange : $eLoginState : $eLoginStateReason")
    }

    override fun onAvailabilityChange(eAvailability: NexmoLoginListener.EAvailability, nexmoConnectionState: NexmoConnectionState) {
        Log.d(TAG, "NexmoLoginListener.onAvailabilityChange : $eAvailability : $nexmoConnectionState")
    }
}

var currentUser: NexmoUser? = null
var currentCall: NexmoCall? = null
lateinit var contextRef: WeakReference<Context>
private var didInit: Boolean = false

fun init(appContext: Context) {
    Log.d(TAG, "login, did init? " + didInit)
    if (didInit) {
        return
    }
    didInit = true
    contextRef = WeakReference(appContext)
    NexmoClient.init(NexmoClient.NexmoClientConfig(), appContext, loginListener)
}

val userName: String?
    get() = currentUser?.name

val otherUserName: String?
    get() = if (currentUser?.name == USER_NAME_JANE) USER_NAME_JOE else USER_NAME_JANE


