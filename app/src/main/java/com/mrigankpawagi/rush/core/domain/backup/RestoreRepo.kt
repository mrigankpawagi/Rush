package com.mrigankpawagi.rush.core.domain.backup

interface RestoreRepo {
    suspend fun restoreSongs(path: String): RestoreResult
}