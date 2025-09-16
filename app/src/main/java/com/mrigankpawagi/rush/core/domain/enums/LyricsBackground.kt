package com.mrigankpawagi.rush.core.domain.enums

import com.mrigankpawagi.rush.R
import com.mrigankpawagi.rush.core.presentation.hypnoticAvailable

enum class LyricsBackground(val stringRes: Int) {
    HYPNOTIC(R.string.hypnotic),
    ALBUM_ART(R.string.blurred_art),
    SOLID_COLOR(R.string.solid_color);

    companion object {
        val allBackgrounds = if (hypnoticAvailable()) {
            listOf(SOLID_COLOR, ALBUM_ART, HYPNOTIC)
        } else {
            listOf(SOLID_COLOR, ALBUM_ART)
        }
    }
}