package com.abidev.airnode.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.fragment.app.Fragment
import com.abidev.airnode.R
import com.abidev.airnode.databinding.FragmentMapBinding
import com.abidev.airnode.updateActionBarTitle

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

        // Make background transparent
        mapContainer.setBackgroundColor(0)

        // Load map view and enable Javascript
        val webSettings: WebSettings = mapContainer.settings
        true.also { webSettings.javaScriptEnabled = it }
        mapContainer.loadUrl("https://airnode.web.app/ux/mapa.html")

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}