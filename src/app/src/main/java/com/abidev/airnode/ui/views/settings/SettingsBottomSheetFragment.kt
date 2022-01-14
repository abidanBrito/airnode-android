package com.abidev.airnode.ui.views.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.abidev.airnode.R
import com.abidev.airnode.core.LoginState
import com.abidev.airnode.core.SIGN_IN_TEXT
import com.abidev.airnode.core.SIGN_OUT_TEXT
import com.abidev.airnode.core.goTo
import com.abidev.airnode.databinding.FragmentSettingsBottomSheetBinding
import com.abidev.airnode.ui.views.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth

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
        // Inflate layout for this dialog fragment and get binding reference
        _binding = FragmentSettingsBottomSheetBinding.inflate(
            inflater,
            container,
            false
        )

        updateLoginMenuItem()

        binding.apply {
            // Event listener for login link
            loginContainer.setOnClickListener {
                when (it.tag) {
                    LoginState.IN.name -> {
                        (activity as? MainActivity)?.loginStatus = false

                        FirebaseAuth.getInstance().signOut()
                        dismiss()
                    }

                    LoginState.OUT.name -> {
                        (activity as? MainActivity)?.loginStatus = true

                        // Update GUI
                        goTo(R.id.action_settingsBottomSheetFragment_to_signInFragment)
                        (activity as MainActivity).run {
                            swapActionBar()
                            toggleBars()
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun updateLoginMenuItem() {
        binding.apply {
            // Update login status in menu
            if ((activity as? MainActivity)!!.loginStatus) {
//            if (loginContainer.tag.equals(LoginState.IN.name)) {
                loginContainer.tag = LoginState.IN.name
                loginText.text = SIGN_OUT_TEXT
            } else {
                loginContainer.tag = LoginState.OUT.name
                loginText.text = SIGN_IN_TEXT
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
