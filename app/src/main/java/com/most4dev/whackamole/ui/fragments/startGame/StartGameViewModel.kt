package com.most4dev.whackamole.ui.fragments.startGame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.most4dev.domain.usecases.GetBestScoreUseCase

class StartGameViewModel(
    private val getBestScoreUseCase: GetBestScoreUseCase
): ViewModel() {

    private var _bestScore = MutableLiveData<Long>()
    val bestScore: LiveData<Long> = _bestScore

    init {
        startGame()
    }

    private fun startGame(){
        val bestScore = getBestScoreUseCase()
        _bestScore.value = bestScore
    }

}