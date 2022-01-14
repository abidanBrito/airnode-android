package com.abidev.airnode.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abidev.airnode.R
import com.abidev.airnode.core.*
import com.abidev.airnode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnScrollListenerInterface {
    private lateinit var binding: ActivityMainBinding

    var loginStatus = false

    // Lazy NavController reference
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
        setSupportActionBar(binding.customActionbar.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        binding.run {
            // Hide go back icon
            customActionbar.goBackIcon.hideView()

            // Link the Bottom Navigation View to the Navigation Controller
            navView.setupWithNavController(navController)

            // Settings icon listener
            customActionbar.settingsIcon.setOnClickListener {
                navController.navigate(R.id.settingsBottomSheetFragment)
            }

            // ActionBar listeners
            customActionbar.goBackIcon.setOnClickListener {
                onBackPressed()
            }
            customActionbarGoBack.goBackIcon.setOnClickListener {
                onBackPressed()
            }
        }

        if (intent.extras?.get("loginState") as Boolean) {
            loginStatus = true
        }
    }

    fun changeAppBarTitle(title: String) {
        binding.customActionbar.toolbarTitle.text = title
    }

    fun toggleBars() {
        toggleActionBar()
        toggleNavBar()
    }

    private fun toggleActionBar() {
        if (supportActionBar?.isShowing == true) {
            supportActionBar!!.hide()
            return
        }

        supportActionBar!!.show()
    }

    private fun toggleNavBar() {
        binding.run {
            if (navView.visibility == View.VISIBLE) {
                navView.hideView()
                return
            }

            navView.showView()
        }
    }

    fun swapActionBar() {
        binding.run {
            if (supportActionBar?.isShowing == true) {
                customActionbar.root.hideView()
                customActionbarGoBack.root.showView()
            } else {
                customActionbar.root.showView()
                customActionbarGoBack.root.hideView()
            }
        }
    }

    override fun hideBars(duration: Int, heightActionBar: Float, heightNavBar: Float) {
        binding.run {
            customActionbar.appbar.slideUpAnimation(duration, heightActionBar)
            navView.slideDownAnimation(duration, heightNavBar)
        }
    }

    override fun showBars(duration: Int, defaultHeight: Float) {
        binding.run {
            customActionbar.appbar.slideDownAnimation(duration, defaultHeight)
            navView.slideUpAnimation(duration, defaultHeight)
        }
    }

    override fun onBackPressed() {
        // Note(abi): this next line is here for debugging purposes
        //binding.customToolbarTitle.text = navController.currentDestination?.label
        if (navController.currentDestination?.id == R.id.signInFragment) {
            swapActionBar()
            toggleBars()
        }

        super.onBackPressed()
    }

    // NOTE(abi): I just found this online. It might come in handy.
//    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}