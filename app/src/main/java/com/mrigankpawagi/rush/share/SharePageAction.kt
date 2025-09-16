package com.mrigankpawagi.rush.share

import com.mrigankpawagi.rush.core.domain.enums.AlbumArtShape
import com.mrigankpawagi.rush.core.domain.enums.CardColors
import com.mrigankpawagi.rush.core.domain.enums.CardFit
import com.mrigankpawagi.rush.core.domain.enums.CardTheme
import com.mrigankpawagi.rush.core.domain.enums.CornerRadius
import com.mrigankpawagi.rush.core.domain.enums.Fonts

sealed interface SharePageAction {
    data object OnShowPaywall : SharePageAction
    data class OnUpdateAlbumArtShape(val shape: AlbumArtShape) : SharePageAction
    data class OnUpdateCardTheme(val theme: CardTheme) : SharePageAction
    data class OnUpdateCardColor(val color: CardColors) : SharePageAction
    data class OnUpdateCardFit(val fit: CardFit) : SharePageAction
    data class OnUpdateCardRoundness(val roundness: CornerRadius) : SharePageAction
    data class OnUpdateCardContent(val color: Int): SharePageAction
    data class OnUpdateCardBackground(val color: Int): SharePageAction
    data class OnUpdateCardFont(val font: Fonts) : SharePageAction
}