package com.example.gleb.mediumme.model

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

class PostDetailsModel: IPostDetailsModel, GoogleApiClient.ConnectionCallbacks, LocationListener {
    val LOG_TAG: String = this.javaClass.canonicalName
    val MILLISECOND: Int = 1000
    val INTERVAL_LOCATION_UPDATES = 10
    var googleService: GoogleApiClient? = null

    override fun buildGoogleService(context: Context): GoogleApiClient {
        Log.d(LOG_TAG, "Model builde Google Service Cleint")
        googleService = GoogleApiClient.Builder(context).addApi(LocationServices.API).addConnectionCallbacks(this).build()
        return googleService!!
    }

    override fun onConnected(p0: Bundle?) {
        Log.d(LOG_TAG, "Google Service is connected")
        var lRequest = initLocationRequest()
        var location = LocationServices.FusedLocationApi.getLastLocation(googleService)
        checkLocation(location, lRequest)
    }

    override fun onConnectionSuspended(p0: Int) {
        Log.d(LOG_TAG, "Google Service is disconnected")
    }

    override fun initLocationRequest(): LocationRequest {
        var lRequest : LocationRequest = LocationRequest.create()
        lRequest.interval = (INTERVAL_LOCATION_UPDATES * MILLISECOND).toLong()
        lRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        return lRequest
    }

    override fun checkLocation(location: Location, lRequest: LocationRequest) {
        if (location == null){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleService, lRequest, this)
        }
        else{
            Log.d(LOG_TAG, "Location lat " + location.latitude + " lon " + location.longitude)
        }
    }

    override fun onLocationChanged(p0: Location?) {
        Log.d(LOG_TAG, "Location lat " + p0!!.latitude + " lon " + p0!!.longitude)
    }
}