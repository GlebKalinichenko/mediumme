package com.example.gleb.mediumme.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gleb.mediumme.fragments.PostListFragment
import com.example.gleb.mediumme.R
import com.example.gleb.mediumme.fragments.BaseFragment
import com.example.gleb.mediumme.helper.FragmentHelper
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {
    val LOG_TAG: String = this.javaClass.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLoadFragment()
    }

    fun initLoadFragment(){
        var fragment: BaseFragment = PostListFragment()
        FragmentHelper.loadFragment(this, R.id.layout_container, fragment)
    }

//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 0) supportFragmentManager.popBackStack()
//        else super.onBackPressed()
//    }
}
