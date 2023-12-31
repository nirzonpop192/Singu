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
import com.exam.singularity.core.hasInternetConnection
import com.exam.singularity.core.listener.ItemOnClickListener
import com.exam.singularity.core.toast
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

    private var mPage=1

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


        adapterStore = StoreAdapter(requireContext())
        binding.rvStore.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvStore.adapter = adapterStore


        if (hasInternetConnection())
            mainViewModel.getStores(1)

        binding.rvStore.addOnScrollListener( object :
            RecyclerView.OnScrollListener(){


            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
                   if ( mainViewModel.isReadyToLoad.value==true){
                       mPage++
                       mainViewModel.getStores(mPage)
                   }
                }
            }


        })



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
                        if (adapterStore.modelList.isEmpty())
                        adapterStore.submitData(ArrayList(it.body.data))
                        else
                            adapterStore.updateData(ArrayList(it.body.data))

                        mainViewModel.isReadyToLoad.value = it.body.links?.next !=null
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