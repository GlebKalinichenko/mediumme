package com.example.gleb.mediumme.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.gleb.mediumme.entities.PostEntityResponse

interface IListPostsView: PostImageItemClick {
    fun displayListPosts(list: List<PostEntityResponse>)
    fun initWidgets(v: View)
    fun initWidgetActions()
}