package com.example.gleb.mediumme.model

import android.content.Context
import android.location.Location
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest

interface IPostDetailsModel {
    fun buildGoogleService(context: Context): GoogleApiClient
    fun initLocationRequest(): LocationRequest
    fun checkLocation(location: Location, lRequest: LocationRequest)
}
