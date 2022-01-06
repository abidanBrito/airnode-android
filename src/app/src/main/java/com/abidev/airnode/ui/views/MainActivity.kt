package com.abidev.airnode.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abidev.airnode.R
import com.abidev.airnode.core.OnScrollListenerInterface
import com.abidev.airnode.core.slideDownAnimation
import com.abidev.airnode.core.slideUpAnimation
import com.abidev.airnode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnScrollListenerInterface {
    private lateinit var binding: ActivityMainBinding

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

        // Hide go back icon
        binding.customActionbar.goBackIcon.visibility = View.GONE

        // Link the Bottom Navigation View to the Navigation Controller
        binding.navView.setupWithNavController(navController)

        // Settings icon listener
        binding.customActionbar.settingsIcon.setOnClickListener {
            navController.navigate(R.id.settingsBottomSheetFragment)
        }
    }

    // NOTE(abi): I just found this online. It might come in handy.
//    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

    fun changeAppBarTitle(title: String) {
        binding.customActionbar.toolbarTitle.text = title
    }

    fun toggleActionBarAndNavBar() {
        if (supportActionBar?.isShowing == true) {
            supportActionBar!!.hide()
            binding.navView.visibility = View.GONE
            return
        }

        supportActionBar!!.show()
        binding.navView.visibility = View.VISIBLE
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
            toggleActionBarAndNavBar()
        }
        super.onBackPressed()
    }
}