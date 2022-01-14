package com.abidev.airnode.ui.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abidev.airnode.R
import com.abidev.airnode.core.updateActionBarTitle
import com.abidev.airnode.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        // Change ActionBar title
        updateActionBarTitle(R.string.title_dashboard)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // NOTE(abi): this has to be set to null to avoid memory leaks!
        _binding = null
    }
}