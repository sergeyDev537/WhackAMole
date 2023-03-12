package com.most4dev.whackamole.ui.fragments.game

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.navigation.fragment.findNavController
import com.most4dev.domain.entities.GameResultEntity
import com.most4dev.whackamole.R
import com.most4dev.whackamole.databinding.FragmentGameBinding
import com.most4dev.whackamole.ui.base.BaseFragment
import com.most4dev.whackamole.utils.shakeView
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class GameFragment : BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate) {

    private val gameViewModel by viewModel<GameViewModel>()

    private lateinit var imageViewMole: ImageView
    private lateinit var frameLayout: FrameLayout

    private val arrayImages: Array<Array<FrameLayout?>> =
        Array(ARRAY_IMAGE_SIZE) { arrayOfNulls(ARRAY_IMAGE_SIZE) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserves()
    }

    private fun setObserves() {
        gameViewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
            setGameView()
        }
        gameViewModel.score.observe(viewLifecycleOwner) {
            binding.tvScore.text = it.toString()
        }
        gameViewModel.gameResult.observe(viewLifecycleOwner) {
            launchFragmentGameResult(it)
        }
    }

    private fun launchFragmentGameResult(gameResult: GameResultEntity) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToResultGameFragment(
                gameResult
            )
        )
    }

    private fun setGameView() {
        val randomRow = Random.nextInt(COUNTER_ROWS)
        val randomColumn = Random.nextInt(COUNTER_COLUMNS)
        for (i in 0 until COUNTER_ROWS) {
            for (j in 0 until COUNTER_COLUMNS) {
                setSettingsFrame()
                setSettingsMole(i, randomRow, j, randomColumn)
                frameLayout.addView(imageViewMole)
                arrayImages[i][j] = frameLayout
            }
        }
        binding.placeGame.removeAllViewsInLayout()
        setSettingsTableRow()
    }

    private fun setSettingsTableRow() {
        for (i in 0 until COUNTER_ROWS) {
            val tableRow = TableRow(requireContext())
            tableRow.layoutParams = getParamsFrameLayout()
            tableRow.gravity = Gravity.CENTER
            for (j in 0 until COUNTER_COLUMNS) {
                tableRow.addView(arrayImages[i][j], j)
            }
            binding.placeGame.addView(tableRow, i)
        }
    }

    private fun setSettingsMole(
        i: Int,
        randomRow: Int,
        j: Int,
        randomColumn: Int,
    ) {
        if (i == randomRow && j == randomColumn) {
            imageViewMole.setImageResource(R.drawable.mole)
            imageViewMole.setOnClickListener {
                imageViewMole.visibility = View.GONE
                gameViewModel.clickRight()
            }
            imageViewMole.shakeView()
        }
    }

    private fun setSettingsFrame() {
        frameLayout = FrameLayout(requireContext())
        imageViewMole = ImageView(requireContext())
        val paramsImage = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        paramsImage.setMargins(
            DEFAULT_IMAGE_MARGIN_LEFT,
            DEFAULT_IMAGE_MARGIN_TOP,
            DEFAULT_IMAGE_MARGIN_RIGHT,
            DEFAULT_IMAGE_MARGIN_BOTTOM
        )
        imageViewMole.layoutParams = paramsImage
        val params = getParamsFrameLayout()
        params.setMargins(
            DEFAULT_MARGINS,
            DEFAULT_MARGINS,
            DEFAULT_MARGINS,
            DEFAULT_MARGINS
        )
        frameLayout.layoutParams = params
        frameLayout.setBackgroundResource(R.drawable.der)
    }

    private fun getParamsFrameLayout(): TableRow.LayoutParams {
        return TableRow.LayoutParams(
            DEFAULT_WIDTH_ROW,
            DEFAULT_HEIGHT_ROW,
            DEFAULT_WEIGHT_PARAMS
        )
    }

    companion object {
        const val COUNTER_ROWS = 3
        const val COUNTER_COLUMNS = 3
        const val DURATION_ANIMATION = 500L
        const val DEFAULT_MARGINS = 8
        const val ARRAY_IMAGE_SIZE = 3
        const val DEFAULT_WEIGHT_PARAMS = 1.0F
        const val DEFAULT_VALUE_FIRST = 30F
        const val DEFAULT_VALUE_SECOND = 0F
        const val DEFAULT_VALUE_THIRD = 0F
        const val DEFAULT_WIDTH_ROW = 200
        const val DEFAULT_HEIGHT_ROW = 200
        const val DEFAULT_IMAGE_MARGIN_TOP = 0
        const val DEFAULT_IMAGE_MARGIN_LEFT = 0
        const val DEFAULT_IMAGE_MARGIN_RIGHT = 0
        const val DEFAULT_IMAGE_MARGIN_BOTTOM = 24
    }

}