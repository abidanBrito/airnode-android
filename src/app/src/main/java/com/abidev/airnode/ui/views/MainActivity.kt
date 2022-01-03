package com.abidev.airnode.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.abidev.airnode.R
import com.abidev.airnode.core.slideDownAnimation
import com.abidev.airnode.core.slideUpAnimation
import com.abidev.airnode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnScrollListenerMain {
    private lateinit var binding: ActivityMainBinding

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

        // Get NavController reference
        val navController by lazy {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navHostFragment.navController
        }

        // Link the Bottom Navigation View to the Navigation Controller
        binding.navView.setupWithNavController(navController)

        // Settings icon listener
        binding.customActionbar.settingsIcon.setOnClickListener {
//            navController.navigate(R.id.settingsBottomSheetFragment, navOptions {
//                anim {
//                    enter =
//                }
//            })
            navController.navigate(R.id.settingsBottomSheetFragment)
        }
    }

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
}

interface OnScrollListenerMain {
    fun hideBars(duration: Int, heightActionBar: Float, heightNavBar: Float)
    fun showBars(duration: Int, defaultHeight: Float)
}