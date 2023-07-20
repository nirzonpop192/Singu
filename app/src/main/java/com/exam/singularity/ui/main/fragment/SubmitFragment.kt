package com.exam.singularity.ui.main.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.exam.singularity.R
import com.exam.singularity.core.BaseFragment
import com.exam.singularity.core.log
import com.exam.singularity.core.toast
import com.exam.singularity.databinding.FragmentSubmitBinding
import com.exam.singularity.ui.main.viewmodel.MainViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ulid.ULID


class SubmitFragment : BaseFragment(), LocationListener {
    private lateinit var binding: FragmentSubmitBinding

    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var locationManager: LocationManager

    private val locationPermissionCode = 2
    private var mLatitude = 0.0
    private var mLongitude = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubmitBinding.inflate(inflater)
        return binding.root
    }

    override fun setUpView(savedInstanceState: Bundle?) {
        getLocation()
    }

    override fun observeClickEvents() {
        binding.btnSubmit.setOnClickListener {
            setAttendance()

        }
    }
    private fun setAttendance(){
        val _name=binding.edtName.text.toString()
        val _userId=binding.edtUserId.text.toString()
        val _requestId=ULID.randomULID()
        if (validate(_name,_userId,mLatitude,mLongitude)){
            mainViewModel.setAttendance(_name,_userId.toInt(),mLatitude,mLongitude,_requestId)
        }
    }

    override fun observeViewModelEvents() {
        lifecycleScope.launch {
            mainViewModel.setAttendanceResult.collectLatest {
                when (it) {
                    is NetworkResponse.Success -> {
                      it.body.user_message?.toast(requireContext())
                    }
                    is NetworkResponse.Error -> {

                    }
                }
            }
        }
    }

    private fun validate(name:String, userId:String, lat:Double, lon:Double): Boolean {
        val errorColor= ContextCompat.getColor(requireContext(), R.color.Red)
        val normalColor= ContextCompat.getColor(requireContext(), R.color.black)
        binding.tiName.setBoxStrokeColor(normalColor)
        binding.tiUserId.setBoxStrokeColor(normalColor)
        binding.layoutErrorName.isVisible=false
        binding.layoutErrorId.isVisible=false
        if (name.isEmpty())
        {
            binding.tiName.setBoxStrokeColor(errorColor)
            binding.layoutErrorName.isVisible=true
            binding.tiName.isFocusable=true
            return false
        }

        else if (userId.isEmpty()){
            binding.tiUserId.setBoxStrokeColor(errorColor)
            binding.layoutErrorId.isVisible=true
            binding.tiName.isFocusable=true

            return false
        }
        else if(lat==0.0)
            return false
        else if (lon==0.0)
            return false

        return true
    }

    private fun getLocation() {
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (checkLocationPermission()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        } else
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    fun checkLocationPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
    }

    override fun onLocationChanged(location: Location) {

        var gpsLocation: String =
            "Latitude: " + location.latitude + " , Longitude: " + location.longitude
        mLatitude = location.latitude
        mLongitude = location.longitude
        gpsLocation.log("dim")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                "Permission Granted".toast(requireContext())
                getLocation()
            } else {
                "Permission Denied".toast(requireContext())
            }
        }
    }
}