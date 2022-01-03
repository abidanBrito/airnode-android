package com.abidev.airnode.core

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.abidev.airnode.ui.views.MainActivity

fun Fragment.updateActionBarTitle(@StringRes id: Int) {
    (activity as? MainActivity)?.changeAppBarTitle(getString(id))
}