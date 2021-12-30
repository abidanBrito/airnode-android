package com.abidev.airnode

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abidev.airnode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout and initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up custom Action Bar
        setSupportActionBar(binding.appbarLayout.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // Hide go back icon
        binding.appbarLayout.goBackIcon.visibility = View.GONE

        // Get NavController reference
        val navController by lazy {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navHostFragment.navController
        }

        // Link the Bottom Navigation View to the Navigation Controller
         binding.navView.setupWithNavController(navController)

        // Settings icon listener
        binding.appbarLayout.settingsIcon.setOnClickListener {
            navController.navigate(R.id.settingsBottomSheetFragment)
        }
    }

    fun changeAppBarTitle(title: String) {
        binding.appbarLayout.toolbarTitle.text = title
    }
}