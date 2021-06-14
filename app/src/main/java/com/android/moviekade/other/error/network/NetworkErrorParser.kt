package com.android.moviekade.other.error.network

import com.android.moviekade.other.error.ErrorEntity
import com.android.moviekade.other.error.ErrorParser

class NetworkErrorParser: ErrorParser {
    override fun parseToCode(errorEntity: ErrorEntity): Int {
        return when (errorEntity) {
            is NetworkErrorEntity.Io -> 1
            is NetworkErrorEntity.NotFound -> 2
            is NetworkErrorEntity.AccessDenied -> 3
            is NetworkErrorEntity.NoConnect -> 4
            is NetworkErrorEntity.ServiceUnavailable -> 5
            is NetworkErrorEntity.BadRequest -> 6
            is NetworkErrorEntity.TimeOut -> 7
            is NetworkErrorEntity.UnAuthorized -> 8
            is NetworkErrorEntity.Authoritative -> 9
            is NetworkErrorEntity.PaymentRequired -> 10
            is NetworkErrorEntity.NotAcceptable -> 11
            is NetworkErrorEntity.ProxyAuth -> 12
            is NetworkErrorEntity.Conflict -> 13
            is NetworkErrorEntity.Gone -> 14
            is NetworkErrorEntity.InternalError -> 15
            is NetworkErrorEntity.GatewayTimeout -> 16
            is NetworkErrorEntity.UseProxy -> 17
            is NetworkErrorEntity.Unknown -> 0
            else -> 0
        }
    }

    override fun parseToString(errorEntity: ErrorEntity): String {
        return when(errorEntity) {
            is NetworkErrorEntity.Io -> 1.toString()
            is NetworkErrorEntity.NotFound -> 2.toString()
            is NetworkErrorEntity.AccessDenied -> 3.toString()
            is NetworkErrorEntity.NoConnect -> 4.toString()
            is NetworkErrorEntity.ServiceUnavailable -> 5.toString()
            is NetworkErrorEntity.BadRequest -> 6.toString()
            is NetworkErrorEntity.TimeOut -> 7.toString()
            is NetworkErrorEntity.UnAuthorized -> 8.toString()
            is NetworkErrorEntity.Authoritative -> 9.toString()
            is NetworkErrorEntity.PaymentRequired -> 10.toString()
            is NetworkErrorEntity.NotAcceptable -> 11.toString()
            is NetworkErrorEntity.ProxyAuth -> 12.toString()
            is NetworkErrorEntity.Conflict -> 13.toString()
            is NetworkErrorEntity.Gone -> 14.toString()
            is NetworkErrorEntity.InternalError -> 15.toString()
            is NetworkErrorEntity.GatewayTimeout -> 16.toString()
            is NetworkErrorEntity.UseProxy -> 17.toString()
            is NetworkErrorEntity.Unknown -> 0.toString()
            else -> 0.toString()
        }
    }
}