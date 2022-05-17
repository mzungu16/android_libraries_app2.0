package com.gleb.anroid_libraries_app2.ui.myIntentServiceScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gleb.anroid_libraries_app2.R
import com.gleb.anroid_libraries_app2.databinding.FragmentMyIntentServiceBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MyIntentServiceFragment : Fragment() {
    private val binding by viewBinding(FragmentMyIntentServiceBinding::class.java)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_intent_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = MyIntentService()
        val intent = Intent()
        val intent1 = Intent()
        val intent2 = Intent()
        service.startMyService(intent.putExtra("timer", 3_000).putExtra("value", 1))
        service.startMyService(intent1.putExtra("timer", 1_000).putExtra("value", 2))
        service.startMyService(intent2.putExtra("timer", 3_000).putExtra("value", 3))
        binding.btnStart.setOnClickListener {
            service.handleIntent()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    binding.text.text = it
                }, {
                    Log.d("TAG", "${it.printStackTrace()}")
                })
        }
    }

}