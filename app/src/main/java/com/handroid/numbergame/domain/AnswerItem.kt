package com.handroid.numbergame.domain

import android.graphics.Color

data class AnswerItem(
    var id: Int,
    var number: Int,
    var color: Color,
    var isTrue:Boolean
)