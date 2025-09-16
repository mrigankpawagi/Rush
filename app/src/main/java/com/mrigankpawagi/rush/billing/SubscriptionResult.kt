package com.mrigankpawagi.rush.billing

sealed interface SubscriptionResult {
    data object Subscribed : SubscriptionResult
    data object NotSubscribed : SubscriptionResult
    data class Error(val e: Throwable) : SubscriptionResult
}