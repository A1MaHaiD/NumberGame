package com.handroid.numbergame.domain.use_cases

import com.handroid.numbergame.domain.entity.GameSettings
import com.handroid.numbergame.domain.entity.Level
import com.handroid.numbergame.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val gameRepository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return gameRepository.getGameSettings(level)
    }
}