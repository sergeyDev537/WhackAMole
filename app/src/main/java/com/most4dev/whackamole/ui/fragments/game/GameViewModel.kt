package com.most4dev.whackamole.ui.fragments.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.most4dev.domain.entities.GameResultEntity
import com.most4dev.domain.usecases.FinishGameUseCase
import com.most4dev.domain.usecases.GetBestScoreUseCase
import com.most4dev.domain.usecases.SetBestScoreUseCase
import com.most4dev.domain.usecases.StartGameUseCase

class GameViewModel(
    private val getBestScoreUseCase: GetBestScoreUseCase,
    private val startGameUseCase: StartGameUseCase,
    private val setBestScoreUseCase: SetBestScoreUseCase,
    private val finishGameUseCase: FinishGameUseCase
) : ViewModel() {

    private var timer: CountDownTimer? = null

    private var _score = MutableLiveData(0L)
    val score: LiveData<Long> = _score

    private var _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var _gameResult = MutableLiveData<GameResultEntity>()
    val gameResult: LiveData<GameResultEntity>
        get() = _gameResult

    init {
        startGame()
    }

    private fun startGame() {
        val gameSettings = startGameUseCase()
        _score.value = 0
        val time = gameSettings.millisTimer
        startTimer(time)
    }

    private fun startTimer(time: Long) {
        timer = object : CountDownTimer(
            time,
            MILLIS_VISIBLE
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }

        }
        timer?.start()
    }

    private fun finishGame() {
        _gameResult.value = finishGameUseCase(_score.value!!)
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    fun clickRight() {
        _score.value = _score.value?.plus(1)
        checkBestScore(_score.value!!)
    }

    private fun checkBestScore(score: Long) {
        val bestScore = getBestScoreUseCase()
        if (score > bestScore) {
            setBestScore(score)
        }
    }

    private fun setBestScore(bestScore: Long) {
        setBestScoreUseCase(bestScore)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {

        const val MILLIS_IN_SECONDS = 1000L
        const val SECONDS_IN_MINUTES = 60
        const val MILLIS_VISIBLE = 500L

    }


}