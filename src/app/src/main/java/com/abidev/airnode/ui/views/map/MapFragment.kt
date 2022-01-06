package com.abidev.airnode.ui.views.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.view.get
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

        // Initialize and load map
        initializeMap()
        setUpMapWebClient()
        loadInterpolationMap()

        // GUI listeners
        setUpReloadButton()
        setUpFullScreenToggle()
        setUpGasButtons()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // NOTE(abi): this has to be set to null to avoid memory leaks!
        _binding = null
    }

    private fun setUpReloadButton() {
        binding.apply {
            btnReloadMap.setOnClickListener {
                btnReloadMap.hideView()
                mapContainer.hideView()
                mapProgressBar.showView()

                failedLoadingMap = false

                // NOTE(abi): this delay is technically not necessary, but it provides
                // a better UX (ProgressBar doesn't flash) if there's no Internet connection
                lifecycleScope.launch {
                    delay(250L)
                    loadInterpolationMap()
                }
            }
        }
    }

    private fun initializeMap() {
        // Get map container and enable scrollbars
        val mapContainer = binding.mapContainer
        mapContainer.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        // Make background transparent
        mapContainer.setBackgroundColor(0)

        // Get map webView and enable Javascript
        val webSettings: WebSettings = mapContainer.settings
        true.also { webSettings.javaScriptEnabled = it }

        // Disable loading from cache (map is non-responsive when offline)
        mapContainer.settings.cacheMode = WebSettings.LOAD_NO_CACHE
    }

    private fun setUpFullScreenToggle() {
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
            (activity as? MainActivity)?.toggleBars()

            // Reset margins
            it.setMarginsDp(top = topMargin)
            binding.mapLegend.setMarginsDp(top = topMargin)
            binding.mapButtons.setMarginsDp(bottom = bottomMargin)
        }
    }

    private fun setUpGasButtons() {
        binding.run {
            // CO
            btnCO.setOnClickListener {
                initializeButton(it as FrameLayout, CO_HTML_ID)
            }

            // NO2
            btnNO2.setOnClickListener {
                initializeButton(it as FrameLayout, NO2_HTML_ID)
            }

            // O3
            btnO3.setOnClickListener {
                initializeButton(it as FrameLayout, O3_HTML_ID)
            }

            // SO2
            btnSO2.setOnClickListener {
                initializeButton(it as FrameLayout, SO2_HTML_ID)
            }
        }
    }

    private fun initializeButton(btnContainer: FrameLayout, htmlID: String) {
        binding.mapContainer.evaluateJavascript("document.getElementById('${htmlID}').click()") {}
        disableAllButtons(R.color.inactiveGrey)
        (btnContainer[0] as TextView).setColor(R.color.colorSecondary)
    }

    private fun setUpMapWebClient() {
        binding.apply {
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
                    mapProgressBar.hideView()
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
                        mapContainer.showView()
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
                mapContainer.showView()
                mapFullscreen.showView()
                mapLegend.showView()
                mapButtons.showView()
                return
            }

            btnReloadMap.showView()
        }
    }

    private fun toggleFullscreenIcon() {
        binding.run {
            if (fullscreenIcon.tag.equals(MAP_MINIMIZED)) {
                fullscreenIcon.setImageResource(R.drawable.ic_close)
                fullscreenIcon.tag = MAP_MAXIMIZED
            } else {
                fullscreenIcon.setImageResource(R.drawable.ic_full_screen)
                fullscreenIcon.tag = MAP_MINIMIZED
            }
        }
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