package com.handroid.numbergame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSetting(
    val maxSumValue: Int,
    var minCountOfRightAnswers: Int,
    var minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Parcelable
