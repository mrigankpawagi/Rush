package com.mrigankpawagi.rush.share

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.mrigankpawagi.rush.core.domain.data_classes.ExtractedColors
import com.mrigankpawagi.rush.core.domain.data_classes.SongDetails
import com.mrigankpawagi.rush.core.domain.enums.AlbumArtShape
import com.mrigankpawagi.rush.core.domain.enums.CardColors
import com.mrigankpawagi.rush.core.domain.enums.CardFit
import com.mrigankpawagi.rush.core.domain.enums.CardTheme
import com.mrigankpawagi.rush.core.domain.enums.CornerRadius
import com.mrigankpawagi.rush.core.domain.enums.Fonts

data class SharePageState(
    val songDetails: SongDetails = SongDetails(),
    val selectedLines: Map<Int, String> = emptyMap(),
    val extractedColors: ExtractedColors = ExtractedColors(),

    val cardFont: Fonts = Fonts.POPPINS,
    val cardColors: CardColors = CardColors.MUTED,
    val cardBackground: Int = Color.Gray.toArgb(),
    val cardContent: Int = Color.White.toArgb(),
    val cardFit: CardFit = CardFit.FIT,
    val cardRoundness: CornerRadius = CornerRadius.ROUNDED,
    val cardTheme: CardTheme = CardTheme.SPOTIFY,
    val albumArtShape: AlbumArtShape = AlbumArtShape.COOKIE_12,

    val isProUser: Boolean = false
)