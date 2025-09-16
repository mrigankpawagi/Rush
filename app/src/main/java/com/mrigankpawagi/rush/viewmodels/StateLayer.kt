package com.mrigankpawagi.rush.viewmodels

import com.mrigankpawagi.rush.lyrics.LyricsPageState
import com.mrigankpawagi.rush.saved.SavedPageState
import com.mrigankpawagi.rush.search_sheet.SearchSheetState
import com.mrigankpawagi.rush.setting.SettingsPageState
import com.mrigankpawagi.rush.share.SharePageState
import kotlinx.coroutines.flow.MutableStateFlow

// all the states in a single place so that they can be updated from different viewmodels
class StateLayer {
    val lyricsState = MutableStateFlow(LyricsPageState())
    val searchSheetState = MutableStateFlow(SearchSheetState())
    val savedPageState = MutableStateFlow(SavedPageState())
    val sharePageState = MutableStateFlow(SharePageState())
    val settingsState = MutableStateFlow(SettingsPageState())
}