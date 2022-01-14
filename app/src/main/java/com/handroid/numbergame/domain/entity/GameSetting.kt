package com.handroid.numbergame.domain.entity

import java.io.Serializable

data class GameSetting(
    val maxSumValue: Int,
    var minCountOfRightAnswers: Int,
    var minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Serializable