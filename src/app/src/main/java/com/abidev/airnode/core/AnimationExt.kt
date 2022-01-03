package com.abidev.airnode.core

import android.view.View

fun View.slideUpAnimation(duration: Int, hideOnEnd: Boolean) {
    val v: View = this
    v.animate()
        .translationY(v.height.toFloat())
        .setListener(null)
        .duration = duration.toLong()
}

fun View.slideUpAnimation(duration: Int, height: Float) {
    val v: View = this
    v.animate()
        .translationY(height)
        .setListener(null)
        .duration = duration.toLong()
}

fun View.slideDownAnimation(duration: Int, hideOnEnd: Boolean) {
    val v: View = this
    v.animate()
        .translationY(v.height.toFloat())
        .setListener(null)
//        .setListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator?) {
//                if (hideOnEnd) {
//                    v.hideView()
//                }
//            }
//        })
        .duration = duration.toLong()
}

fun View.slideDownAnimation(duration: Int, height: Float) {
    val v: View = this
    v.animate()
        .translationY(height)
        .setListener(null)
//        .setListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator?) {
//                super.onAnimationEnd(animation)
//                if (hideOnEnd) {
//                    v.hideView()
//                }
//            }
//        })
        .duration = duration.toLong()
}

// TODO(abi): fix weird alpha bug.
fun View.slideDownFadeOutAnimation(duration: Int, height: Float) {
    val v: View = this
    v.animate()
        .translationY(height)
        .alpha(0.5F)
        .setListener(null)
//        .setListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator?) {
//                super.onAnimationEnd(animation)
//                v.hideView()
//            }
//        })
        .duration = duration.toLong()
}

fun View.slideDownFadeInAnimation(duration: Int, height: Float) {
    val v: View = this
    v.animate()
        .translationY(height)
        .alpha(0.0F)
        .setListener(null)
        .duration = duration.toLong()
}

fun View.slideUpFadeInAnimation(duration: Int, height: Float) {
    val v: View = this
    v.animate()
        .translationY(height)
        .alpha(0.0F)
        .setListener(null)
        .duration = duration.toLong()
}

fun View.slideUpFadeOutAnimation(duration: Int, height: Float) {
    val v: View = this
    v.animate()
        .translationY(height)
        .alpha(0.5F)
        .setListener(null)
//        .setListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator?) {
//                super.onAnimationEnd(animation)
//                v.hideView()
//            }
//        })
        .duration = duration.toLong()
}