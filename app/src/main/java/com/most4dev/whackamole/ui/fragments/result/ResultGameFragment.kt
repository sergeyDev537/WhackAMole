package com.most4dev.whackamole.ui.fragments.result

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.most4dev.whackamole.R
import com.most4dev.whackamole.databinding.FragmentResultGameBinding
import com.most4dev.whackamole.ui.base.BaseFragment

class ResultGameFragment :
    BaseFragment<FragmentResultGameBinding>(FragmentResultGameBinding::inflate) {

    private val args by navArgs<ResultGameFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setData()
    }

    private fun setClickListeners() {
        binding.buttonPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultGameFragment_to_gameFragment)
        }
        binding.buttonMenu.setOnClickListener {
            findNavController().popBackStack(R.id.startGameFragment, false)
        }
    }

    private fun setData() {
        binding.tvScore.text = args.gameResult.currentScore.toString()
        binding.tvBestscore.text = args.gameResult.bestScore.toString()
    }
}