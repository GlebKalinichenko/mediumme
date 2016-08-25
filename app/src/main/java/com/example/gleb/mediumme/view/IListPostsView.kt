package com.example.gleb.mediumme.view

import android.view.View
import com.example.gleb.mediumme.entities.PostEntityResponse

interface IListPostsView {
    fun displayListPosts(list: List<PostEntityResponse>)
    fun initWidgets(v: View)
}