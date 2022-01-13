package com.handroid.numbergame.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.handroid.numbergame.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private lateinit var binding: FragmentGameFinishedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameFinishedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}