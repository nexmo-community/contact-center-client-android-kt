package com.nexmo.example.contactcenterstarter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.nexmo.client.NexmoCallEventListener
import com.nexmo.client.NexmoCallMember
import com.nexmo.client.NexmoCallStatus
import com.nexmo.client.NexmoMediaActionState

import java.lang.ref.WeakReference

class FinishOnCallEnd(activity: AppCompatActivity) : NexmoCallEventListener {

    private val activityRef: WeakReference<AppCompatActivity> = WeakReference(activity)

    override fun onMemberStatusUpdated(nexmoCallStatus: NexmoCallStatus, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onMemberStatusUpdated: " + callMember.member.user.name + " : " + nexmoCallStatus)

        if (nexmoCallStatus == NexmoCallStatus.COMPLETED || nexmoCallStatus == NexmoCallStatus.CANCELED) {
            activityRef.get()?.finish()
            currentCall = null
        }
    }

    override fun onMuteChanged(nexmoMediaActionState: NexmoMediaActionState, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onMuteChanged: " + callMember.member.user.name + " : " + nexmoMediaActionState)
    }

    override fun onEarmuffChanged(nexmoMediaActionState: NexmoMediaActionState, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onEarmuffChanged: " + callMember.member.user.name + " : " + nexmoMediaActionState)
    }

    override fun onDTMF(dtmf: String, callMember: NexmoCallMember) {
        Log.d(TAG, "NexmoCallEventListener.onDTMF: " + callMember.member.user.name + " : " + dtmf)
    }
}
