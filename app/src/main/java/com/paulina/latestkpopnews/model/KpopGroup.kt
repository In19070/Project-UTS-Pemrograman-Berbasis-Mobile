package com.paulina.latestkpopnews.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class KpopGroup(
    @StringRes val nameResourceId: Int,
    @DrawableRes val imageResourceId: Int
)