package com.example.gleb.mediumme.view

import android.view.View

interface IPostDetailsView {
    fun initWidgets(v: View, displayImage: () -> Unit)
}