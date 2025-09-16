package com.mrigankpawagi.rush.core.domain

import com.mrigankpawagi.rush.core.domain.enums.AlbumArtShape
import com.mrigankpawagi.rush.core.domain.enums.CardColors
import com.mrigankpawagi.rush.core.domain.enums.CardFit
import com.mrigankpawagi.rush.core.domain.enums.CardTheme
import com.mrigankpawagi.rush.core.domain.enums.CornerRadius
import com.mrigankpawagi.rush.core.domain.enums.Fonts
import kotlinx.coroutines.flow.Flow

interface SharePagePreferences {
    fun getCardFitFlow(): Flow<CardFit>
    suspend fun updateCardFit(newCardFit: CardFit)

    fun getCardBackgroundFlow(): Flow<Int>
    suspend fun updateCardBackground(newCardBackground: Int)

    fun getCardContentFlow(): Flow<Int>
    suspend fun updateCardContent(newCardContent: Int)

    fun getCardThemeFlow(): Flow<CardTheme>
    suspend fun updateCardTheme(newCardTheme: CardTheme)

    fun getCardColorFlow(): Flow<CardColors>
    suspend fun updateCardColor(newCardColor: CardColors)

    fun getCardRoundnessFlow(): Flow<CornerRadius>
    suspend fun updateCardRoundness(newCardRoundness: CornerRadius)

    fun getCardFontFlow(): Flow<Fonts>
    suspend fun updateCardFont(newCardFont: Fonts)

    fun getAlbumArtShapeFlow(): Flow<AlbumArtShape>
    suspend fun updateAlbumArtShape(shape: AlbumArtShape)
}