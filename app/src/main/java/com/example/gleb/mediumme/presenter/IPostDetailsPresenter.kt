package com.example.gleb.mediumme.presenter

import android.content.Context
import com.google.android.gms.common.api.GoogleApiClient

interface IPostDetailsPresenter {
    fun onStart()
    fun onResume(context: Context)
    fun onPause()
    fun onStop()
    fun initLocationService(context: Context): GoogleApiClient
}