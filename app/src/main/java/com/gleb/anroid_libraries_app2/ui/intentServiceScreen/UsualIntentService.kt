package com.gleb.anroid_libraries_app2.ui.intentServiceScreen

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.TimeUnit


class UsualIntentService : IntentService("myThread") {
    val ACTION_INTENT_SERVICE = "RESPONSE"
    private var result = "intentService"
    private val handler = Handler(Looper.getMainLooper())
    override fun onHandleIntent(intent: Intent?) {
        val timer = intent?.getIntExtra("timer", 0)
        val value = intent?.getIntExtra("value", 0)
        Log.d("TAG", "onHandleIntent start: $value");
        try {
            if (timer != null) {
                TimeUnit.SECONDS.sleep(timer.toLong())
                handler.post {
                    result = value.toString()
                    val responseIntent = Intent()
                    responseIntent.action = ACTION_INTENT_SERVICE
                    responseIntent.addCategory(Intent.CATEGORY_DEFAULT)
                    responseIntent.putExtra("resultValue", result)
                    sendBroadcast(responseIntent)
                }
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.d("TAG", "onHandleIntent end: $value")
    }
}