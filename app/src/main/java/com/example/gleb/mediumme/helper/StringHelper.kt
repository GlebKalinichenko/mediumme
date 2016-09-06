package com.example.gleb.mediumme.helper

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

    fun getString(resId: Int, context: Context): String{
        return context.getString(resId)
    }

    fun getString(resId: Int, context: Context, values: String): String{
        return context.getString(resId, values)
    }
}