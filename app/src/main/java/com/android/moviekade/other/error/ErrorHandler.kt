package com.android.moviekade.other.error

interface ErrorHandler {
    fun <T> getError(error: T): ErrorEntity
}