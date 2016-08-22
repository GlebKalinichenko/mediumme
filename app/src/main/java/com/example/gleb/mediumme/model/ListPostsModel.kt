package com.example.gleb.mediumme.model

import com.example.gleb.mediumme.presenter.IListPostsPresenter
import com.example.gleb.mediumme.api.ApiService
import com.example.gleb.mediumme.entities.PostBaseResponse
import com.example.gleb.mediumme.model.IListPostsModel
import rx.Observable

data class ListPostsModel (val presenter: IListPostsPresenter) : IListPostsModel {
    val apiService: ApiService = ApiService()

    override fun listPosts(limit: String) {
        var list: Observable<PostBaseResponse> = apiService.listPosts(limit)
        presenter.displayPosts(list)
    }
}