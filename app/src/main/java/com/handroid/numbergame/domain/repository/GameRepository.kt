package com.handroid.numbergame.domain.repository

import com.handroid.numbergame.domain.entity.GameSetting
import com.handroid.numbergame.domain.entity.Level
import com.handroid.numbergame.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue:Int,
        countOfOptions:Int
    ):Question

    fun getGameSettings(level: Level):GameSetting
}