package com.abidev.airnode.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.abidev.airnode.MainActivity
import com.abidev.airnode.R
import com.abidev.airnode.databinding.FragmentSettingsBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SettingsBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSettingsBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.RoundBottomSheetDialogStyle);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate layout for this dialog fragment
        // View binding
        _binding = FragmentSettingsBottomSheetBinding.inflate(inflater, container, false)

        //val navController = NavHostFragment.findNavController(this)

        // Event listener for login link
//        binding.loginContainer.setOnClickListener {
//            (activity as MainActivity).toggleInterfaceItems()
//            navController.navigate(R.id.signin_fragment)
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}