package com.android.moviekade.business.domain.mapper

interface MutualMapper<F,T>: Mapper<F, T> {
    override fun mapFrom(value: F): T
    fun mapTo(value: T): F
}