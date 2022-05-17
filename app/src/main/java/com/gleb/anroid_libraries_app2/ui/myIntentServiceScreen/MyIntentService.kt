package com.gleb.anroid_libraries_app2.ui.myIntentServiceScreen

import android.content.Intent
import io.reactivex.rxjava3.core.Observable

class MyIntentService {
    private val intentList = mutableListOf<Intent>()
    fun startMyService(intent: Intent) {
        intentList.add(intent)
    }

    fun handleIntent(): Observable<String> {
        return Observable.create { subscriber ->
            for (i in intentList) {
                Thread.sleep(i.getIntExtra("timer", 0).toLong())
                subscriber.onNext(i.getIntExtra("value", 0).toString())
            }
        }
    }
}