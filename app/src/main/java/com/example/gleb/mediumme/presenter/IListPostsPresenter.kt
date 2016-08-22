package com.example.gleb.mediumme.presenter

import com.example.gleb.mediumme.entities.PostBaseResponse
import rx.Observable

interface IListPostsPresenter {
    fun listPosts(limit: String)
    fun displayPosts(list: Observable<PostBaseResponse>)
}
