package com.abidev.airnode.core

import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.abidev.airnode.R
import com.abidev.airnode.ui.views.MainActivity

fun Fragment.updateActionBarTitle(@StringRes id: Int) {
    (activity as? MainActivity)?.changeAppBarTitle(getString(id))
}

fun Fragment.goTo(@IdRes action: Int) {
    NavHostFragment.findNavController(this)
        .navigate(action)
}