package com.handroid.numbergame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCount: Int,
    val minPercent: Int,
    val gameTimeInSeconds: Int
) : Parcelable{
    val minCountString:String
    get() = minCount.toString()
    val minPercentString: String
    get() = minPercent.toString()
}
