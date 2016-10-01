package com.example.gleb.mediumme.presenter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v4.app.DialogFragment
import android.util.Log
import com.example.gleb.mediumme.model.IPostDetailsModel
import com.example.gleb.mediumme.model.PostDetailsModel
import com.example.gleb.mediumme.view.IPostDetailsView
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices

class PostDetailsPresenter (var view: IPostDetailsView?): IPostDetailsPresenter {
    val REQUEST_ERROR_CODE: Int = 0
    val LOG_TAG: String = this.javaClass.canonicalName;
    val model: IPostDetailsModel = PostDetailsModel()

    override fun onStart() {
        Log.d(LOG_TAG, "On start details connect to location service")
    }

    override fun onResume(context: Context) {
        var errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context)
        if (errorCode != ConnectionResult.SUCCESS){
            var dialog: Dialog = GooglePlayServicesUtil.getErrorDialog(errorCode, context as Activity?, REQUEST_ERROR_CODE, DialogInterface.OnCancelListener { i -> context.finish() } )
            dialog.show()
        }
    }

    override fun onStop() {
        Log.d(LOG_TAG, "Disconnect from location service")

    }

    override fun onPause() {

    }

    override fun initLocationService(context: Context): GoogleApiClient {
        Log.d(LOG_TAG, "Initialize location of user");
        return null!!
    }

}