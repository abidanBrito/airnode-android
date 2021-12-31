package com.abidev.airnode.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.abidev.airnode.R

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun View.setMarginsDp(
    left: Int = this.marginLeft,
    top: Int = this.marginTop,
    right: Int = this.marginRight,
    bottom: Int = this.marginBottom,
) {
    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(left, top, right, bottom)
    }
}

fun Context.newToast(msg: String, _duration: Int) {
    // Get Activity
    val activity: Activity? = this as? Activity

    // Inflate custom Toast layout
    val layout = activity!!.layoutInflater.inflate(
        R.layout.custom_toast,
        activity.findViewById(R.id.cl_customToastContainer)
    )

    // Set message
    layout!!.findViewById<TextView>(R.id.tv_toast).text = msg

    // Set view, duration and gravity, and show it
    Toast(activity).apply {
        view = layout
        duration = _duration
        setGravity(Gravity.BOTTOM, 0, 240)
        show()
    }
}