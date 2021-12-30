package com.abidev.airnode

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.updateActionBarTitle(@StringRes newTitle : Int) {
    (requireContext() as? MainActivity)?.changeAppBarTitle(getString(newTitle))
}