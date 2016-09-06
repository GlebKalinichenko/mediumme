package com.example.gleb.mediumme.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.example.gleb.mediumme.BaseFragment

class FragmentHelper {
    val LOG_TAG: String = this.javaClass.canonicalName

    companion object {
        fun loadFragment(context: FragmentActivity, resId: Int, fragment: BaseFragment){
            context.supportFragmentManager.beginTransaction().add(resId, fragment).addToBackStack(null).commit()
        }

        fun <T : Fragment> reloadFragment(context: FragmentActivity, resId: Int, fragment: T){
            context.supportFragmentManager.beginTransaction().replace(resId, fragment).commit()
        }
    }

}