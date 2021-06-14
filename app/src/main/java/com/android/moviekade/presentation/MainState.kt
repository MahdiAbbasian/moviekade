package com.android.moviekade.presentation

sealed class MainState <out T> {
    data class Loaded<T>(var data:T):MainState<T>()
    data class Error(var text:String):MainState<Nothing>()
    object Loading:MainState<Nothing>()
}