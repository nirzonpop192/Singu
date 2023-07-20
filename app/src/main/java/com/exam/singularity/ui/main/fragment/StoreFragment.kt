package com.exam.singularity.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.singularity.R
import com.exam.singularity.core.BaseFragment
import com.exam.singularity.core.listener.ItemOnClickListener
import com.exam.singularity.databinding.FragmentStoreBinding
import com.exam.singularity.ui.main.adapter.StoreAdapter
import com.exam.singularity.ui.main.model.StoreResponse
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

    lateinit var adapterStore: StoreAdapter
    override fun setUpView(savedInstanceState: Bundle?) {

//        mainViewModel.getStores(1)
        adapterStore = StoreAdapter(requireContext())
        binding.rvStore.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvStore.adapter = adapterStore

//        lifecycleScope.launch {
//            adapterStore.loadStateFlow.collectLatest { loadStates ->
////                bottomProductBinding.loaderAnimation.isVisible = loadStates.refresh is LoadState.Loading
//                binding.rvStore.isVisible != loadStates.refresh is LoadState.Loading
//            }
//        }




//        mainViewModel.getStoresPagging()
        mainViewModel.getStores(1)

    }

    override fun observeClickEvents() {
        adapterStore.clickItem = object : ItemOnClickListener<StoreResponse.StoreDataModel> {
            override fun onClick(data: StoreResponse.StoreDataModel) {

                findNavController().navigate(R.id.submitFragment)
            }
        }
    }

    override fun observeViewModelEvents() {
        lifecycleScope.launch {
            mainViewModel.getStoresResult.collectLatest {
                when (it) {
                    is NetworkResponse.Success -> {
                        adapterStore.submitData(ArrayList(it.body.data))
                    }
                    is NetworkResponse.Error -> {

                    }
                }
            }
        }
//        lifecycleScope.launch {
//            mainViewModel.getStoresPaggingResult.collectLatest {
//
//
//                adapterStore.submitData(it)
//
//            }
//        }
    }
}