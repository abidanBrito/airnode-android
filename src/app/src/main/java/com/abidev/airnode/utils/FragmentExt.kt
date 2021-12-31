package com.abidev.airnode.utils

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.abidev.airnode.MainActivity

fun Fragment.updateActionBarTitle(@StringRes id: Int) {
    (requireContext() as? MainActivity)?.changeAppBarTitle(getString(id))
}