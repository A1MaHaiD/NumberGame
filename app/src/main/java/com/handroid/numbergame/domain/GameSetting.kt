package com.handroid.numbergame.domain

import java.util.*

data class GameSetting(
    var maxSumScore: Int? = null,
    var timeQuiz: Date? = null,
    var minCountAnswer:Int? = null,
    var minPercentAnswer:Int? = null
)
