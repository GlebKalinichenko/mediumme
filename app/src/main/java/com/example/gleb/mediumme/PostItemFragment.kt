package com.example.gleb.mediumme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PostItemFragment: BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.fragment_post_item, container, false)

        return v
    }

}