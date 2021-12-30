package com.abidev.airnode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abidev.airnode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout and initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up custom Action Bar
        setSupportActionBar(binding.appbarLayout.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // Link the Bottom Navigation View to the Navigation Controller
        binding.navView.setupWithNavController(navController)
    }

    fun changeAppBarTitle(title: String) {
        binding.appbarLayout.toolbarTitle.text = title
    }
}