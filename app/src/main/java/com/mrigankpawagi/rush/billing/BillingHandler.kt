package com.mrigankpawagi.rush.billing

interface BillingHandler {
    suspend fun isProUser(): Boolean
    suspend fun userResult(): SubscriptionResult
}