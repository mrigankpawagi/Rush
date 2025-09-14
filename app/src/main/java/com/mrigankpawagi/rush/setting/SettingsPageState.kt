package com.mrigankpawagi.rush.setting

import com.mrigankpawagi.rush.core.domain.backup.ExportState
import com.mrigankpawagi.rush.core.domain.backup.RestoreState
import com.mrigankpawagi.rush.core.domain.data_classes.Theme

data class SettingsPageState(
    val theme: Theme = Theme(),
    val deleteButtonEnabled: Boolean = true,
    val exportState: ExportState = ExportState.IDLE,
    val restoreState: RestoreState = RestoreState.IDLE,
    val isProUser: Boolean = false,
    val onBoardingDone: Boolean = true,
    val showPaywall: Boolean = false,
    val searchRefinement: String = ""
)