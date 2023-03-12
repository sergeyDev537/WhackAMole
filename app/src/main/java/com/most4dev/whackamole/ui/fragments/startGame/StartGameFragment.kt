package com.most4dev.whackamole.ui.fragments.startGame

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.most4dev.whackamole.R
import com.most4dev.whackamole.databinding.FragmentStartGameBinding
import com.most4dev.whackamole.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartGameFragment : BaseFragment<FragmentStartGameBinding>(
    FragmentStartGameBinding::inflate
) {

    private val startGameViewModel by viewModel<StartGameViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setObserves()
    }

    private fun setClickListeners() {
        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(R.id.action_startGameFragment_to_gameFragment)
        }

        binding.buttonRules.setOnClickListener {
            findNavController().navigate(R.id.action_startGameFragment_to_rulesFragment)
        }
    }

    private fun setObserves() {
        startGameViewModel.bestScore.observe(viewLifecycleOwner){
            binding.tvBestscore.text = it.toString()
        }
    }
}