package com.abidev.airnode.ui.views.auth

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.abidev.airnode.R
import com.abidev.airnode.core.goTo
import com.abidev.airnode.core.hideView
import com.abidev.airnode.core.showView
import com.abidev.airnode.databinding.FragmentSignInBinding
import com.abidev.airnode.ui.views.MainActivity
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View binding
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        // Update bars
        (activity as MainActivity).run {
            swapActionBar()
            toggleBars()
        }

        binding.apply {
            // Sign up link
            tvSignUpLink.setOnClickListener {
                goTo(R.id.action_signInFragment_to_signUpFragment)
            }

            // Lost password link
            tvRecoverPassword.setOnClickListener {
                goTo(R.id.action_signInFragment_to_recoverPasswordFragment)
            }

            // Toggle show / hide custom password icons
            inputPassword.setEndIconOnClickListener {
                inputPassword.clearOnEndIconChangedListeners()
                inputPassword.endIconMode = END_ICON_PASSWORD_TOGGLE
                inputPassword.endIconDrawable =
                    getDrawable(requireContext(), R.drawable.show_hide_password_icon_selector)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}