package com.handroid.numbergame.domain.entity

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
) {
    val rightAnswers: Int
        get() = sum - visibleNumber
}
