package com.handroid.numbergame.presentation.view_models

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.handroid.numbergame.R
import com.handroid.numbergame.data.GameRepositoryImpl
import com.handroid.numbergame.domain.entity.GameResult
import com.handroid.numbergame.domain.entity.GameSettings
import com.handroid.numbergame.domain.entity.Level
import com.handroid.numbergame.domain.entity.Question
import com.handroid.numbergame.domain.use_cases.GenerateQuestionUseCase
import com.handroid.numbergame.domain.use_cases.GetGameSettingsUseCase

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level
    private var timer: CountDownTimer? = null

    private val context = application

    private val repository = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question
    private val _percent = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int>
        get() = _percent
    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers
    private val _enoughCount = MutableLiveData<Boolean>()
    val enoughCount: LiveData<Boolean>
        get() = _enoughCount
    private val _enoughPercent = MutableLiveData<Boolean>()
    val enoughPercent: LiveData<Boolean>
        get() = _enoughPercent
    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent
    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    fun startGame(level: Level) {
        getGameSettings(level)
        startTimer()
        generateQuestion()
        updateProgress()
    }

    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _percent.value = percent
        _progressAnswers.value = String.format(
            context.resources.getString(R.string.progress_answers),
            countOfRightAnswers,
            gameSettings.minCount
        )
        _enoughCount.value = countOfQuestions >= gameSettings.minCount
        _enoughPercent.value = percent >= gameSettings.minPercent
    }

    private fun calculatePercentOfRightAnswers(): Int {
        if (countOfQuestions == 0) {
            return 0
        }
        return ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    private fun checkAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswers
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
    }

    private fun getGameSettings(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase(level)
        _minPercent.value = gameSettings.minPercent
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formattedTimeToString(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun formattedTimeToString(millisUntilFinished: Long): String {
        val wholeSeconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = wholeSeconds / SECONDS_IN_MINUTE
        val leftSeconds = wholeSeconds - (minutes * SECONDS_IN_MINUTE)
        return String().format("%02d:%02d", minutes, leftSeconds)
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughCount.value == true &&
                    enoughPercent.value == true,
            countOfRightAnswers,
            countOfQuestions,
            gameSettings
        )
    }

    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTE = 60
    }
}