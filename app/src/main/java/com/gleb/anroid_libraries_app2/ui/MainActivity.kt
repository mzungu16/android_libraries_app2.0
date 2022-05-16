package com.gleb.anroid_libraries_app2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gleb.anroid_libraries_app2.R
import com.gleb.anroid_libraries_app2.databinding.ActivityMainBinding
import com.gleb.anroid_libraries_app2.ui.intentServiceScreen.IntentServiceFragment
import com.gleb.anroid_libraries_app2.ui.myIntentServiceScreen.MyIntentServiceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerIntentService.id, IntentServiceFragment())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerMyIntentService.id, MyIntentServiceFragment())
            .commit()
    }
}