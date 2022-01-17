package com.handroid.numbergame.presentation.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.handroid.numbergame.R
import com.handroid.numbergame.domain.entity.GameResult

@BindingAdapter("necessaryAnswers")
fun bindNecessaryAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.necessary_right_answer),
        count
    )
}

@BindingAdapter("yourScore")
fun bindYourScore(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.your_score),
        count
    )
}

@BindingAdapter("necessaryPercentage")
fun bindNecessaryPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.necessary_percent_right_answer),
        count
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    imageView.apply {
        setImageResource(
            if (winner) {
                R.drawable.ic_smile
            } else {
                R.drawable.ic_sad
            }
        )
    }
}

@BindingAdapter("percentYourAnswers")
fun bindPercentYourAnswers(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.percent_your_answers),
        with(gameResult) {
            if (countOfQuestions == 0) {
                0
            } else {
                ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
            }
        }
    )
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}
