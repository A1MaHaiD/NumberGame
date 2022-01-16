package com.handroid.numbergame.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.handroid.numbergame.R
import com.handroid.numbergame.databinding.FragmentGameFinishedBinding
import com.handroid.numbergame.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        getGameResult()
    }

    private fun setClickListeners() {
        binding.btnTryAgain.setOnClickListener {
            tryAgainGame()
        }
    }

    private fun getGameResult() {
        binding.gameResult = args.gameResult
        with(binding){
            ivEmojiResult.setImageResource(getSmileResId())
/*            tvRequiredAnswers.text = String.format(
                getString(R.string.necessary_right_answer),
                args.gameResult.gameSettings.minCount
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.your_score),
                args.gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.necessary_percent_right_answer),
                args.gameResult.gameSettings.minPercent
            )*/
            tvScorePercentage.text = String.format(
                getString(R.string.percentage_right_answers),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getPercentOfRightAnswers() = with (args.gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun getSmileResId(): Int {
        return if (args.gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun tryAgainGame() {
        findNavController().popBackStack()
    }
}