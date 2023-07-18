package com.exam.singularity.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.exam.singularity.core.BaseFragment
import com.exam.singularity.core.log
import com.exam.singularity.databinding.FragmentStoreBinding
import com.exam.singularity.ui.main.viewmodel.MainViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreFragment : BaseFragment() {

    private lateinit var binding: FragmentStoreBinding

    private val mainViewModel by activityViewModels<MainViewModel>()
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
        mainViewModel.getStores(1)
    }

    override fun observeClickEvents() {
        lifecycleScope.launch {
            mainViewModel.getStoresResult.collectLatest {
                when (it) {
                    is NetworkResponse.Success -> {
                        it.body.data?.size.toString().log("dim")
                    }
                    is NetworkResponse.Error -> {

                    }
                }
            }
        }
    }

    override fun observeViewModelEvents() {

    }
}