package com.example.gleb.mediumme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val LOG_TAG: String = this.javaClass.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLoadFragment()
    }

    fun initLoadFragment(){
        var fragment: BaseFragment = PostFragment()
        FragmentHelper.loadFragment(this, R.id.layout_container, fragment)
    }
}
