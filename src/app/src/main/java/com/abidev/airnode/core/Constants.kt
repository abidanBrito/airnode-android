package com.abidev.airnode.core

// INTERPOLATION MAP
const val MAP_URL = "https://airnode.web.app/ux/mapa2.html"
const val ERROR_MAP_URL = "file:///android_asset/error_page/index.html"
const val CO_HTML_ID = "btn-co"
const val NO2_HTML_ID = "btn-co2"
const val O3_HTML_ID = "btn-o3"
const val SO2_HTML_ID = "btn-temp"

enum class MapState {
    MAXIMIZED, MINIMIZED
}

// Firebase Auth
const val SIGN_IN_TEXT = "Iniciar sesión"
const val SIGN_OUT_TEXT = "Cerrar sesión"

enum class LoginState {
    IN, OUT
}