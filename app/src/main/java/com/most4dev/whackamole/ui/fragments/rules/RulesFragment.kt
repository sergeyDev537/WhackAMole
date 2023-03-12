package com.most4dev.whackamole.ui.fragments.rules

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.most4dev.whackamole.databinding.FragmentRulesBinding
import com.most4dev.whackamole.ui.base.BaseFragment

class RulesFragment : BaseFragment<FragmentRulesBinding>(FragmentRulesBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}