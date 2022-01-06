package com.abidev.airnode.core

interface OnScrollListenerInterface {
    fun hideBars(duration: Int, heightActionBar: Float, heightNavBar: Float)
    fun showBars(duration: Int, defaultHeight: Float)
}