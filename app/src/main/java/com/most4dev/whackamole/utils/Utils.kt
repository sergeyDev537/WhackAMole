package com.most4dev.whackamole.utils

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.most4dev.whackamole.ui.fragments.game.GameFragment

fun View.shakeView() {
    ObjectAnimator.ofFloat(
        this,
        View.TRANSLATION_Y,
        GameFragment.DEFAULT_VALUE_FIRST,
        GameFragment.DEFAULT_VALUE_SECOND,
        GameFragment.DEFAULT_VALUE_THIRD
    ).apply {
        interpolator = AccelerateDecelerateInterpolator()
        duration = GameFragment.DURATION_ANIMATION
        start()
    }
}