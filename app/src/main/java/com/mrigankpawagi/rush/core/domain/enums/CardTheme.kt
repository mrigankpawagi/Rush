package com.mrigankpawagi.rush.core.domain.enums

import com.mrigankpawagi.rush.R

enum class CardTheme(
    val stringRes: Int
) {
    SPOTIFY(R.string.spotify),
    RUSHED(R.string.rushed),
    VERTICAL(R.string.vertical),
    COUPLET(R.string.couplet),
    ALBUM_ART(R.string.album_art),

    HYPNOTIC(R.string.hypnotic),
    QUOTE(R.string.quote),
    MESSY(R.string.messy),
    CHAT(R.string.chat),

    ;

    companion object {
        val premiumCards = listOf(
            HYPNOTIC, MESSY, QUOTE, CHAT
        )
    }
}