package com.gleb.anroid_libraries_app2.ui.intentServiceScreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gleb.anroid_libraries_app2.R
import com.gleb.anroid_libraries_app2.databinding.FragmentIntentServiceBinding

class IntentServiceFragment : Fragment() {
    private val binding by viewBinding(FragmentIntentServiceBinding::class.java)
    private val broadcastReceiver by lazy { MyBroadCastReceiver() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intent_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            val intentService = Intent(requireContext(), UsualIntentService()::class.java)
            context?.startService(intentService.putExtra("timer", 3).putExtra("value", 1))
            context?.startService(intentService.putExtra("timer", 1).putExtra("value", 2))
            context?.startService(intentService.putExtra("timer", 2).putExtra("value", 3))
        }
        val intentFilter = IntentFilter(UsualIntentService().ACTION_INTENT_SERVICE)
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
        context?.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(broadcastReceiver)
    }

    inner class MyBroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getStringExtra("resultValue")
            binding.text.text = result
        }
    }
}