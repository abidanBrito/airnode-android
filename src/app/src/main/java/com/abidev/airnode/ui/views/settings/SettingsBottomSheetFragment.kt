package com.abidev.airnode.ui.views.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.abidev.airnode.R
import com.abidev.airnode.core.goTo
import com.abidev.airnode.databinding.FragmentSettingsBottomSheetBinding
import com.abidev.airnode.ui.views.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingsBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSettingsBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.RoundBottomSheetDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate layout for this dialog fragment

        // View binding
        _binding = FragmentSettingsBottomSheetBinding.inflate(inflater, container, false)

        // Event listener for login link
        binding.loginContainer.setOnClickListener {
            goTo(R.id.action_settingsBottomSheetFragment_to_signInFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}