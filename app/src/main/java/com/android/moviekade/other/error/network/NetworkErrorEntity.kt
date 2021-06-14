package com.android.moviekade.other.error.network

import com.android.moviekade.other.error.ErrorEntity

sealed class NetworkErrorEntity: ErrorEntity {

    object Io : NetworkErrorEntity()
    object NotFound : NetworkErrorEntity()
    object AccessDenied : NetworkErrorEntity()
    object ServiceUnavailable : NetworkErrorEntity()
    object BadRequest : NetworkErrorEntity()
    object UseProxy : NetworkErrorEntity()
    object TimeOut : NetworkErrorEntity()
    object UnAuthorized : NetworkErrorEntity()
    object Authoritative : NetworkErrorEntity()
    object NoConnect : NetworkErrorEntity()
    object PaymentRequired : NetworkErrorEntity()
    object NotAcceptable : NetworkErrorEntity()
    object ProxyAuth : NetworkErrorEntity()
    object Conflict : NetworkErrorEntity()
    object Gone : NetworkErrorEntity()
    object InternalError : NetworkErrorEntity()
    object GatewayTimeout : NetworkErrorEntity()
    object Unknown : NetworkErrorEntity()

}
