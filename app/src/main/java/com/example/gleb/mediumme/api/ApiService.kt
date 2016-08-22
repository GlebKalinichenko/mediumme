package com.example.gleb.mediumme.api

import com.example.gleb.mediumme.entities.PostBaseResponse
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import rx.Observable

class ApiService {

    val api: REdditApi = buildApi().create(REdditApi::class.java)

    fun buildApi() : Retrofit = Retrofit.Builder().baseUrl(BuildApiConfig.API_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    fun listPosts(limit: String): Observable<PostBaseResponse> = api.listPosts(limit)
}