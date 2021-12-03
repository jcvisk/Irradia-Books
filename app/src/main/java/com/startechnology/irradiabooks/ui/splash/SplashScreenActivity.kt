package com.startechnology.irradiabooks.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.startechnology.irradiabooks.MainActivity
import com.startechnology.irradiabooks.R

class SplashScreenActivity : AppCompatActivity() {

    companion object{
        const val TIME_SPLASH_SCREEN = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash_screen)



        exitSplasScreen()
    }

    private fun exitSplasScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            this.finish()
        }, TIME_SPLASH_SCREEN)
    }
}