package com.android.moviekade.other.error

interface ErrorParser {
    fun parseToCode(errorEntity: ErrorEntity): Int
    fun parseToString(errorEntity: ErrorEntity): String
}