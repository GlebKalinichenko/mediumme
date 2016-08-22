package com.example.gleb.mediumme.model

import com.example.gleb.mediumme.entities.PostBaseResponse
import rx.Observable
import java.util.*

interface IListPostsModel {
    fun listPosts(limit: String)
}