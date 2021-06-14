package com.android.moviekade.other.error.network

import com.android.moviekade.other.error.ErrorEntity
import com.android.moviekade.other.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class NetworkErrorHandler @Inject constructor(): ErrorHandler {
    override fun <T> getError(error: T): ErrorEntity {
        return when(error) {
            is IOException -> NetworkErrorEntity.Io
            is HttpException -> {
                when (error.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> NetworkErrorEntity.NotFound
                    HttpURLConnection.HTTP_FORBIDDEN -> NetworkErrorEntity.AccessDenied
                    HttpURLConnection.HTTP_UNAVAILABLE -> NetworkErrorEntity.ServiceUnavailable
                    HttpURLConnection.HTTP_BAD_REQUEST -> NetworkErrorEntity.BadRequest
                    HttpURLConnection.HTTP_USE_PROXY -> NetworkErrorEntity.UseProxy
                    HttpURLConnection.HTTP_CLIENT_TIMEOUT -> NetworkErrorEntity.TimeOut
                    HttpURLConnection.HTTP_UNAUTHORIZED -> NetworkErrorEntity.UnAuthorized
                    HttpURLConnection.HTTP_NOT_AUTHORITATIVE -> NetworkErrorEntity.Authoritative
                    HttpURLConnection.HTTP_NO_CONTENT -> NetworkErrorEntity.NoConnect
                    HttpURLConnection.HTTP_PAYMENT_REQUIRED -> NetworkErrorEntity.PaymentRequired
                    HttpURLConnection.HTTP_NOT_ACCEPTABLE -> NetworkErrorEntity.NotAcceptable
                    HttpURLConnection.HTTP_PROXY_AUTH -> NetworkErrorEntity.ProxyAuth
                    HttpURLConnection.HTTP_CONFLICT -> NetworkErrorEntity.Conflict
                    HttpURLConnection.HTTP_GONE -> NetworkErrorEntity.Gone
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkErrorEntity.InternalError
                    HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> NetworkErrorEntity.GatewayTimeout
                    else -> NetworkErrorEntity.Unknown
                }
            }
            else -> NetworkErrorEntity.Unknown
        }
    }
}