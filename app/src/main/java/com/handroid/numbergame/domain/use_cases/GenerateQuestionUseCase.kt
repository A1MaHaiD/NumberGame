package com.handroid.numbergame.domain.use_cases

import com.handroid.numbergame.domain.entity.Question
import com.handroid.numbergame.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val gameRepository: GameRepository
) {
    operator fun invoke(maxSumValue: Int): Question {
        return gameRepository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    companion object {
        const val COUNT_OF_OPTIONS = 6
    }
}