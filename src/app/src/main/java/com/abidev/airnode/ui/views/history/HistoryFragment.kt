package com.abidev.airnode.ui.views.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.abidev.airnode.R
import com.abidev.airnode.core.dpToPx
import com.abidev.airnode.core.updateActionBarTitle
import com.abidev.airnode.databinding.FragmentHistoryBinding
import com.abidev.airnode.ui.views.MainActivity

class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        // Change ActionBar title
        updateActionBarTitle(R.string.title_history)

        // Get parent Activity
        val parentActivity: MainActivity? = activity as? MainActivity

        // Hide / show ActionBar and BottomNavigationView on scroll
        binding.svHistory.run {
            setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) {
                    parentActivity!!.run {
                        hideBars(
                            100,
                            dpToPx(-60F).toFloat(),
                            dpToPx(70F).toFloat(),
                        )
                    }
                } else {
                    parentActivity!!.run {
                        showBars(
                            100,
                            dpToPx(0F).toFloat(),
                        )
                    }
                }
            })

            return binding.root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // NOTE(abi): this has to be set to null to avoid memory leaks!
        _binding = null
    }
}