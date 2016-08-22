package com.example.gleb.mediumme.view

import com.example.gleb.mediumme.entities.PostEntityResponse

interface IListPostsView {
    fun displayListPosts(list: List<PostEntityResponse>)
}