package com.handroid.numbergame.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.handroid.numbergame.R
import com.handroid.numbergame.databinding.FragmentGameFinishedBinding
import com.handroid.numbergame.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArg()
    }

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
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                tryAgainGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        binding.btnTryAgain.setOnClickListener {
            tryAgainGame()
        }
    }

    private fun getGameResult() {
        binding.ivEmojiResult.setImageResource(getSmileResId())
        binding.tvRequiredAnswers.text = String.format(
            getString(R.string.necessary_right_answer),
            gameResult.gameSettings.minCount
        )
        binding.tvScoreAnswers.text = String.format(
            getString(R.string.your_score),
            gameResult.countOfRightAnswers
        )
        binding.tvRequiredPercentage.text = String.format(
            getString(R.string.necessary_percent_right_answer),
            gameResult.gameSettings.minPercent
        )
        binding.tvScorePercentage.text = String.format(
            getString(R.string.percentage_right_answers),
            getPercentOfRightAnswers()
        )
    }

    private fun getPercentOfRightAnswers() = with (gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArg() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun tryAgainGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.TAG,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {
        const val TAG = "GameFinishedFragment"
        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}