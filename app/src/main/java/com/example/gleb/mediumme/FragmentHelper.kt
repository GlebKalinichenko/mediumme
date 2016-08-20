package com.example.gleb.mediumme

import android.support.v4.app.FragmentActivity
import android.util.Log

class FragmentHelper {
    val LOG_TAG: String = this.javaClass.canonicalName

    companion object {
        fun loadFragment(context: FragmentActivity, resId: Int, fragment: BaseFragment){
            context.supportFragmentManager.beginTransaction().add(resId, fragment).commit()
        }
    }

}