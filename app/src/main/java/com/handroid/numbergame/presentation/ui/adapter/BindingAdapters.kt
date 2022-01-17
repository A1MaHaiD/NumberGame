package com.handroid.numbergame.presentation.ui.adapter

import android.widget.ImageView
import android.widget.TextView
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

