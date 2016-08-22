package com.example.gleb.mediumme.api

import com.example.gleb.mediumme.entities.PostBaseResponse
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable
import java.util.*

interface REdditApi {
    @GET("/top.json")
    fun listPosts(@Query("limit") limit: String): Observable<PostBaseResponse>
}