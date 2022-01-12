package com.handroid.numbergame.domain.entity

data class GameSetting(
    val maxSumValue: Int,
    var minCountOfRightAnswers: Int,
    var minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
)
