package com.example.appwhere.home

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appwhere.R
import com.example.appwhere.api.ListenerMerchant
import com.example.appwhere.api.PresenterMerchant
import com.example.appwhere.bases.BaseFragment
import com.example.appwhere.models.Login
import com.example.appwhere.models.Merchant
import com.example.appwhere.utilities.Settings

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : BaseFragment(), OnMapReadyCallback, ListenerMerchant {

    private val cLocationRequestCode = 101
    private lateinit var mMap: GoogleMap
    private var marker: Marker? = null

    private var presenterMerchant: PresenterMerchant? = null

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenterMerchant = PresenterMerchant()
        //presenterMerchant?.listener = requireContext()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //var mapFragment : SupportMapFragment?=null
        //mapFragment = fragmentManager!!.findFragmentById(R.id.map) as SupportMapFragment?
        //mapFragment?.getMapAsync(this)
        startMap()
        getMerchant()
    }

    fun getMerchant() {
        presenterMerchant?.attempMerchant()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            cLocationRequestCode -> {

                if (grantResults.isEmpty() || grantResults[0] !=
                    PackageManager.PERMISSION_GRANTED) {
                    showMessage(Settings.TYPE_MESSAGE_ERROR, getString(R.string.error_title), "Unable to show location - permission required")
                } else {
                    startMap()
                }
            }
        }
    }

    private fun startMap() {
        val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            if (::mMap.isInitialized) {
                mMap.isMyLocationEnabled = true
            } else {
                initMap()
            }
        } else {
            requestPermission(
                Manifest.permission.ACCESS_FINE_LOCATION,
                cLocationRequestCode)
        }
    }

    private fun initMap () {
        var mapFragment : SupportMapFragment?=null
        mapFragment = fragmentManager!!.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun requestPermission(permissionType: String,
                                  requestCode: Int) {

        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(permissionType), requestCode
        )
    }


    override fun showErrorMessage(message: String) {
        if(message != null){
            showMessage(Settings.TYPE_MESSAGE_ERROR, getString(R.string.error_title), message)
        }
    }

    override fun successEntry(merchantt: Merchant) {
        Log.d("TAG", "Merchant: " + merchantt.status)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
