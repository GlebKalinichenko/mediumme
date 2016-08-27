package com.example.gleb.mediumme.helper

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageHelper {

    companion object {
        fun loadImage(context: Context, imageView: ImageView?, url: String){
            Picasso.with(context).load(url).into(imageView)
        }
    }
}