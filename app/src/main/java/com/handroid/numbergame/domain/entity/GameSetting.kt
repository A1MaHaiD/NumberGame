package com.handroid.numbergame.domain.entity

data class GameSetting(
    val maxSumValue: Int,
    val gameTimeInSeconds: Int,
    var minCountOfRightAnswers: Int,
    var minPercentOfRightAnswers: Int
)
