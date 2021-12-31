package com.abidev.airnode.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import com.abidev.airnode.MainActivity
import com.abidev.airnode.R
import com.abidev.airnode.databinding.FragmentMapBinding
import com.abidev.airnode.utils.dpToPx
import com.abidev.airnode.utils.setMarginsDp
import com.abidev.airnode.utils.updateActionBarTitle

class MapFragment : Fragment() {
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

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

        // Prevent opening the browser by create a subclass of WebView
        // and overriding shouldOverrideUrlLoading
        mapContainer.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }

        // Make background transparent
        mapContainer.setBackgroundColor(0)

        // Load map view and enable Javascript
        val webSettings: WebSettings = mapContainer.settings
        true.also { webSettings.javaScriptEnabled = it }
        mapContainer.loadUrl("https://airnode.web.app/ux/mapa.html")

        binding.mapFullscreen.setOnClickListener {
            // Establish margins
            val defaultMargin = requireContext().dpToPx(30F)
            var topMargin: Int = defaultMargin

            if ((requireContext() as? MainActivity)?.supportActionBar?.isShowing == false) {
                topMargin = it.dpToPx(85F)
            }

            // Hide ActionBar and NavBar
            (requireContext() as? MainActivity)?.toggleActionBarAndNavBar()

            // Reset margins
            it.setMarginsDp(top = topMargin)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}