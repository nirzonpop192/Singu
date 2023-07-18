package com.exam.singularity.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exam.singularity.R
import com.exam.singularity.core.BaseFragment
import com.exam.singularity.databinding.FragmentStoreBinding


class StoreFragment : BaseFragment() {

    private lateinit var binding: FragmentStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(inflater)
        return binding.root
    }

    override fun setUpView(savedInstanceState: Bundle?) {

    }

    override fun observeClickEvents() {

    }

    override fun observeViewModelEvents() {

    }
}