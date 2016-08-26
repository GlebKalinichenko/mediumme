package com.example.gleb.mediumme

import android.content.Context
import android.util.Log

class StringHelper private constructor() {
    val LOG_TAG: String = this.javaClass.canonicalName

    init {
        Log.d(LOG_TAG, "Init string helper")
    }

    private object HOLDER {
        val INSTANCE = StringHelper()
    }

    companion object {
        val instance: StringHelper by lazy { HOLDER.INSTANCE }
    }

    fun getString(resId: Int, context: Context){
        context.getString(resId)
    }
}