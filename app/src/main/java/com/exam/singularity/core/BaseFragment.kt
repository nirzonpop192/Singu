package com.exam.singularity.core


import android.os.Bundle

import android.view.View

import androidx.fragment.app.Fragment

import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
abstract class BaseFragment : Fragment() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView(savedInstanceState)
        observeClickEvents()
        observeViewModelEvents()
    }

    abstract fun setUpView(savedInstanceState: Bundle?)
    abstract fun observeClickEvents()
    abstract fun observeViewModelEvents()



}