package com.abidev.airnode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abidev.airnode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout and get an instance (View Binding)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set custom Toolbar
        setSupportActionBar(binding.appbarLayout.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }
}