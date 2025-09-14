package com.mrigankpawagi.rush.core.domain.enums

import com.mrigankpawagi.rush.R

// default is a reserved java keyword
enum class CornerRadius(
    val stringRes: Int
) {
    DEFAULT(R.string.default_),
    ROUNDED(R.string.rounded)
}