package com.mrigankpawagi.rush.core.domain.backup

interface ExportRepo {
    suspend fun exportToJson()
}