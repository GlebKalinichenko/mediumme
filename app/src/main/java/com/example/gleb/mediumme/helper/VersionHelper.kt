package com.example.gleb.mediumme.helper

import android.os.Build

class VersionHelper {
    companion object{
        fun checkVersion(): Boolean {
            val result = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) true else false
            return result
        }
    }
}