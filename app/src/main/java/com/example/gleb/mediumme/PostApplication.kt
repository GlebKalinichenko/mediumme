package com.example.gleb.mediumme

import android.app.Application
import org.greenrobot.eventbus.EventBus

class PostApplication: Application(){
    override fun onCreate() {
        super.onCreate()
//        EventBus().register(this)
    }
}
