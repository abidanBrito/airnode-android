package com.abidev.airnode

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Startup logic loading goes here, before calling MainActivity
        // ...

        startActivity(Intent(this, MainActivity::class.java))

        // Kill the activity so we can't get back to it
        finish()
    }
}