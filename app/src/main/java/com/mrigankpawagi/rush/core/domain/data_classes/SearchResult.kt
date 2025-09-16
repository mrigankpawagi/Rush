package com.mrigankpawagi.rush.core.domain.data_classes

data class SearchResult(
    val title: String,
    val artist: String,
    val album: String?,
    val artUrl: String,
    val url: String,
    val id: Long
)