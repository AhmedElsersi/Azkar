package com.example.azkar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.azkar.R
import com.example.azkar.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*
        binding.ivSplash.alpha = 0f
        binding.ivSplash.animate().setDuration(1500).alpha(1f).withEndAction{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }*/


        // video Path & splash
        val videoPath = "android.resource://$packageName/raw/azkar"
        binding.vvSplash.setVideoPath(videoPath)
        binding.vvSplash.setOnCompletionListener {
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.vvSplash.start()
    }
}