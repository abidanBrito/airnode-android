package com.abidev.airnode.ui.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.abidev.airnode.R
import com.abidev.airnode.databinding.FragmentDashboardBinding
import com.abidev.airnode.core.newToast
import com.abidev.airnode.core.updateActionBarTitle

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

        binding.root.setOnClickListener {
            requireActivity().newToast("No hay animación :(", Toast.LENGTH_SHORT)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // NOTE(abi): this has to be set to null to avoid memory leaks!
        _binding = null
    }
}