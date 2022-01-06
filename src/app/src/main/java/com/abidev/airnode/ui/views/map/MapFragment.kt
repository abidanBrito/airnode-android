package com.abidev.airnode.ui.views.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.abidev.airnode.R
import com.abidev.airnode.core.*
import com.abidev.airnode.databinding.FragmentMapBinding
import com.abidev.airnode.ui.views.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapFragment : Fragment() {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    // Map loading flag
    private var failedLoadingMap = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        // Change ActionBar title
        updateActionBarTitle(R.string.title_map)

        // Get map container and enable scrollbars
        val mapContainer = binding.mapContainer
        mapContainer.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        // Make background transparent
        mapContainer.setBackgroundColor(0)

        // Get map webView and enable Javascript
        val webSettings: WebSettings = mapContainer.settings
        true.also { webSettings.javaScriptEnabled = it }

//        mapContainer.settings.cacheMode = WebSettings.LOAD_NO_CACHE

        // Initialize and load map
        setupMapWebClient()
        loadInterpolationMap()

        binding.apply {
            btnReloadMap.setOnClickListener {
                btnReloadMap.visibility = View.GONE
                mapContainer.visibility = View.GONE
                mapProgressBar.visibility = View.VISIBLE

                failedLoadingMap = false

                // NOTE(abi): this delay is technically not necessary, but it provides
                // a better UX (ProgressBar doesn't flash) if there's no Internet connection
                lifecycleScope.launch {
                    delay(250L)
                    loadInterpolationMap()
                }
            }
        }


        binding.mapFullscreen.setOnClickListener {
            // Update icon
            toggleFullscreenIcon()

            // Establish margins
            val defaultMargin = requireContext().dpToPx(30F)
            var topMargin: Int = defaultMargin
            var bottomMargin: Int = defaultMargin

            if ((activity as? MainActivity)?.supportActionBar?.isShowing == false) {
                topMargin = it.dpToPx(85F)
                bottomMargin = it.dpToPx(85F)
            }

            // Hide ActionBar and NavBar
            (activity as? MainActivity)?.toggleActionBarAndNavBar()

            // Reset margins
            it.setMarginsDp(top = topMargin)
            binding.mapLegend.setMarginsDp(top = topMargin)
            binding.mapButtons.setMarginsDp(bottom = bottomMargin)
        }

        binding.btnCO.setOnClickListener {
            mapContainer.evaluateJavascript("document.getElementById('${"btn-co"}').click()") {}

            binding.apply {
                disableAllButtons(R.color.inactiveGrey)
                tvCO.setColor(R.color.colorSecondary)
            }
        }

        binding.btnNO2.setOnClickListener {
            mapContainer.evaluateJavascript("document.getElementById('${"btn-co2"}').click()") {}

            binding.apply {
                disableAllButtons(R.color.inactiveGrey)
                binding.tvNO2.setColor(R.color.colorSecondary)
            }
        }

        binding.btnO3.setOnClickListener {
            mapContainer.evaluateJavascript("document.getElementById('${"btn-o3"}').click()") {}

            binding.apply {
                disableAllButtons(R.color.inactiveGrey)
                tvO3.setColor(R.color.colorSecondary)
            }
        }

        binding.btnSO2.setOnClickListener {
            mapContainer.evaluateJavascript("document.getElementById('${"btn-temp"}').click()") {}

            binding.apply {
                disableAllButtons(R.color.inactiveGrey)
                tvSO2.setColor(R.color.colorSecondary)
            }
        }

        return binding.root
    }

    private fun setupMapWebClient() {
        binding.apply {
            // Map web client setup
            mapContainer.webViewClient = object : WebViewClient() {
                // Prevent opening the browser by creating a subclass of WebView
                // and overriding shouldOverrideUrlLoading
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
                }

                // Hide / show the proper views when done loading
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    mapProgressBar.visibility = View.GONE
                    setMapGUI()
                }

                // Load custom 404 HTML page on error
                override fun onReceivedError(
                    view: WebView,
                    errorCode: Int,
                    description: String,
                    failingUrl: String
                ) {
                    failedLoadingMap = true
                    mapContainer.loadUrl(ERROR_MAP_URL)

                    // NOTE(abi): we need to delay displaying the WebView (just for a fraction
                    // of a second). Otherwise, you might get a flash of the error before loading
                    // our custom 404 page.
                    lifecycleScope.launch {
                        delay(20L)
                        mapContainer.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun loadInterpolationMap() {
        binding.mapContainer.loadUrl(MAP_URL)
    }

    private fun setMapGUI() {
        binding.apply {
            if (!failedLoadingMap) {
                mapContainer.visibility = View.VISIBLE
                mapFullscreen.visibility = View.VISIBLE
                mapLegend.visibility = View.VISIBLE
                mapButtons.visibility = View.VISIBLE
            } else {
                btnReloadMap.visibility = View.VISIBLE
            }
        }
    }

    private fun toggleFullscreenIcon() {
        binding.run {
            if (fullscreenIcon.tag.equals("minimized")) {
                fullscreenIcon.setImageResource(R.drawable.ic_close)
                fullscreenIcon.tag = "maximized"
            } else {
                fullscreenIcon.setImageResource(R.drawable.ic_full_screen)
                fullscreenIcon.tag = "minimized"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // NOTE(abi): this has to be set to null to avoid memory leaks!
        _binding = null
    }

    private fun disableAllButtons(@ColorRes inactiveColor: Int) {
        binding.run {
            tvCO.setColor(inactiveColor)
            tvNO2.setColor(inactiveColor)
            tvO3.setColor(inactiveColor)
            tvSO2.setColor(inactiveColor)
        }
    }
}