package com.abidev.airnode.ui.views.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.abidev.airnode.R
import com.abidev.airnode.core.LoginState
import com.abidev.airnode.core.goTo
import com.abidev.airnode.core.newToast
import com.abidev.airnode.databinding.FragmentSignUpBinding
import com.abidev.airnode.ui.views.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View binding
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.apply {
            // Sign up link
            tvSignInLink.setOnClickListener {
                goTo(R.id.action_signUpFragment_to_signInFragment)
            }

            btnSignUp.setOnClickListener {
                if (etEmail.text!!.isNotEmpty() && etPassword.text!!.isNotEmpty()) {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(
                            etEmail.text.toString(),
                            etPassword.text.toString()
                        ).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                goTo(R.id.action_signUpFragment_to_mapFragment)

                                // Update GUI
                                (activity as MainActivity).run {
                                    swapActionBar()
                                    toggleBars()
                                }

                            } else {
                                requireActivity().newToast(
                                    "Error registrando el correo.",
                                    Toast.LENGTH_SHORT,
                                    280
                                )
                            }
                        }
                } else {
                    requireActivity().newToast(
                        "Rellene todos los campos.",
                        Toast.LENGTH_SHORT,
                        280
                    )
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}