package com.nexmo.example.contactcenterstarter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.nexmo.client.NexmoCallEventListener
import com.nexmo.client.NexmoCallMember
import com.nexmo.client.NexmoCallMemberStatus
import com.nexmo.client.NexmoMediaActionState

import java.lang.ref.WeakReference

class FinishOnCallEnd(activity: AppCompatActivity) : NexmoCallEventListener {

    private val activityRef: WeakReference<AppCompatActivity> = WeakReference(activity)

    override fun onMemberStatusUpdated(nexmoCallStatus: NexmoCallMemberStatus, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onMemberStatusUpdated: " + callMember.user.name + " : " + nexmoCallStatus)

        if (nexmoCallStatus == NexmoCallMemberStatus.COMPLETED || nexmoCallStatus == NexmoCallMemberStatus.CANCELED) {
            activityRef.get()?.finish()
            currentCall = null
        }
    }

    override fun onMuteChanged(nexmoMediaActionState: NexmoMediaActionState, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onMuteChanged: " + callMember.user.name + " : " + nexmoMediaActionState)
    }

    override fun onEarmuffChanged(nexmoMediaActionState: NexmoMediaActionState, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onEarmuffChanged: " + callMember.user.name + " : " + nexmoMediaActionState)
    }

    override fun onDTMF(dtmf: String, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onDTMF: " + callMember.user.name + " : " + dtmf)
    }

}
