package com.gleb.anroid_libraries_app2.ui.myIntentServiceScreen

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MyIntentService : Service() {
    val ACTION_INTENT_SERVICE = "RESPONSE"
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("TAG", "Get - ${intent.getIntExtra("value", 0)}")
        var value: String = ""
        Observable.just(intent)
            .subscribeOn(Schedulers.computation())
            .map {
                Thread.sleep(it.getIntExtra("timer", 0).toLong())
                value = it.getIntExtra("value", 0).toString()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val responseIntent = Intent()
                responseIntent.action = ACTION_INTENT_SERVICE
                responseIntent.addCategory(Intent.CATEGORY_DEFAULT)
                responseIntent.putExtra("resultValue2", value)
                sendBroadcast(responseIntent)
            }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}