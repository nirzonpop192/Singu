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
import androidx.fragment.app.activityViewModels
import com.exam.singularity.core.BaseFragment
import com.exam.singularity.core.log
import com.exam.singularity.core.toast
import com.exam.singularity.databinding.FragmentSubmitBinding
import com.exam.singularity.ui.main.viewmodel.MainViewModel


class SubmitFragment : BaseFragment(), LocationListener {
    private lateinit var binding: FragmentSubmitBinding

    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private val locationPermissionCode = 2
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

    }

    override fun observeViewModelEvents() {

    }

    private fun getLocation() {
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        } else
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    override fun onLocationChanged(location: Location) {

        var gpsLocation: String =
            "Latitude: " + location.latitude + " , Longitude: " + location.longitude
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
            } else {
                "Permission Denied".toast(requireContext())
            }
        }
    }
}