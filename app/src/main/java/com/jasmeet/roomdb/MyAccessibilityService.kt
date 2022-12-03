package com.jasmeet.roomdb

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

const val TAG = "AshmeetIsNoob"
class MyAccessibilityService :AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

//        val packageName :String = event!!.packageName.toString()
//        val packageManager : PackageManager = this.packageManager
//        try {
//            val applicationInfo:ApplicationInfo = packageManager.getApplicationInfo(packageName,0)
//            val applicationLabel:CharSequence=packageManager.getApplicationLabel(applicationInfo)
//            Log.d(TAG, "onAccessibilityEvent: $applicationLabel")
//
//            if (applicationLabel == "WhatsApp"){
//                Toast.makeText(this,"WhatsApp is opened",Toast.LENGTH_SHORT).show()
//                Log.d("whatsaopp", "onAccessibilityEvent: WhatsApp is opened")
//            }
//
//        }catch (e:PackageManager.NameNotFoundException){
//            Log.d(TAG, "onAccessibi: ${e.message}")
//            Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
//        }

        val packageName= event!!.packageName.toString()

        Log.e("Ashmeet is a boy", "onAccessibilityEvent: $packageName" )

        if (packageName == "com.whatsapp"){
           // Toast.makeText(this,"WhatsApp is opened",Toast.LENGTH_SHORT).show()
            Log.d("whatsaopp", "onAccessibility: WhatsApp is opened")
            Toast.makeText(this,"WhatsApp is opened",Toast.LENGTH_SHORT).show()
        }

        else{
            //Toast.makeText(this,"WhatsApp is not opened",Toast.LENGTH_SHORT).show()
            Log.d("whatsaopp", "onAccessibilityEvent: WhatsApp is not opened")
            Toast.makeText(this,"WhatsApp is not opened",Toast.LENGTH_SHORT).show()

            Toast.makeText(applicationContext,"WhatsApp is not opened",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onInterrupt() {
        Log.d(TAG, "onInterrupt:something went wrong ")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        val info : AccessibilityServiceInfo = AccessibilityServiceInfo()

        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or
                AccessibilityEvent.TYPE_VIEW_FOCUSED

        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

        info.notificationTimeout = 2

        this.serviceInfo = info

        Log.d(TAG, "onServiceConnected: ")








    }

}
