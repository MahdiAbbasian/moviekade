package com.android.moviekade.business.domain.mapper

interface Mapper<F,T> {
    fun mapFrom(value:F):T
}
