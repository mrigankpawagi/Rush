package com.mrigankpawagi.rush.billing

class BillingHandlerImpl: BillingHandler {
    override suspend fun isProUser(): Boolean = true
    override suspend fun userResult(): SubscriptionResult = SubscriptionResult.Subscribed
}