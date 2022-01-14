package com.abidev.airnode.ui.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // NOTE(abi): any startup logic loading goes here
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    public override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly
        var isLoggedIn = false
        auth.currentUser?.let {
            isLoggedIn = true
        }

        startActivity(
            Intent(this, MainActivity::class.java)
                .putExtra("loginState", isLoggedIn)
        )

        // Kill the activity so we can't get back to it
        finish()
    }
}