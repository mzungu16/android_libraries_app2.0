package com.gleb.anroid_libraries_app2.ui.myIntentServiceScreen

import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gleb.anroid_libraries_app2.R
import com.gleb.anroid_libraries_app2.databinding.FragmentMyIntentServiceBinding
import com.gleb.anroid_libraries_app2.ui.intentServiceScreen.UsualIntentService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MyIntentServiceFragment : Fragment() {
    private val binding by viewBinding(FragmentMyIntentServiceBinding::class.java)
    private val broadcastReceiver by lazy { MyIntentBroadCastReceiver() }
    /* private val connection = object : ServiceConnection {
         override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
             val service = (p1 as MyIntentService.MyBinder).service
             service.setCallBack { intent ->
                 Observable.just(intent)
                     .subscribeOn(Schedulers.newThread())
                     .map {
                         Thread.sleep(it.getIntExtra("timer", 0).toLong())
                     }
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe {
                         binding.text.text = intent.getIntExtra("value", 0).toString()
                     }
             }
         }

         override fun onServiceDisconnected(p0: ComponentName?) {
             TODO("Not yet implemented")
         }

     }
 */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_intent_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(context, MyIntentService::class.java)
        binding.btnStart.setOnClickListener {
            context?.startService(intent.putExtra("timer", 3_000).putExtra("value", 1))
            context?.startService(intent.putExtra("timer", 1_000).putExtra("value", 2))
            context?.startService(intent.putExtra("timer", 2_000).putExtra("value", 3))
        }
        val intentFilter2 = IntentFilter(MyIntentService().ACTION_INTENT_SERVICE)
        intentFilter2.addCategory(Intent.CATEGORY_DEFAULT)
        context?.registerReceiver(broadcastReceiver, intentFilter2)
    }

    inner class MyIntentBroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getStringExtra("resultValue2")
           binding.text.text = result
        }
    }
}