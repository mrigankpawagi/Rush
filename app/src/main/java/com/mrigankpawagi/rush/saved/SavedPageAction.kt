package com.mrigankpawagi.rush.saved

import com.mrigankpawagi.rush.core.domain.data_classes.Song
import com.mrigankpawagi.rush.core.domain.enums.SortOrder

sealed interface SavedPageAction {
    data object OnToggleAutoChange : SavedPageAction
    data object OnToggleSearchSheet: SavedPageAction
    data class OnDeleteSong(val song: Song) : SavedPageAction
    data class ChangeCurrentSong(val id: Long): SavedPageAction
    data class UpdateSortOrder(val sortOrder: SortOrder): SavedPageAction
}