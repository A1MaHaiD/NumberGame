package com.handroid.numbergame.domain.entity

import java.io.Serializable

data class GameResult(
    val winner: Boolean = false,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSetting
) : Serializable
