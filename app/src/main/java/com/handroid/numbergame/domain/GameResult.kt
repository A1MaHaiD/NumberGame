package com.handroid.numbergame.domain

data class GameResult(
    val relation:Boolean = false,
    val countRightAnswers: Int,
    val score:Int,
    val necessaryAnswerPercent:Int,
    val currentAnswerPercent:Int
)
