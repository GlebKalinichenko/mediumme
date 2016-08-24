package com.example.gleb.mediumme.presenter

import android.util.Log
import com.example.gleb.mediumme.view.IListPostsView
import com.example.gleb.mediumme.entities.PostBaseResponse
import com.example.gleb.mediumme.entities.PostEntityResponse
import com.example.gleb.mediumme.model.IListPostsModel
import com.example.gleb.mediumme.model.ListPostsModel
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

data class ListPostsPresenter (val view: IListPostsView) : IListPostsPresenter {
    val LOG_TAG: String = this.javaClass.canonicalName
    val model: IListPostsModel = ListPostsModel(this)

    override fun listPosts(limit: String) {
        model.listPosts(limit)
    }

    override fun displayPosts(list: Observable<PostBaseResponse>) {
        fun receivePosts(list: Observable<PostBaseResponse>){
            list.flatMap { i -> Observable.just(i.data) }.flatMap { i -> Observable.from(i.children) }
                .flatMap { i -> Observable.just(i.data) }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .reduce(listOf<PostEntityResponse>(), { arr, b -> arr.plus(b)} )
                    .subscribe { i -> i.forEach { v -> Log.d(LOG_TAG + "Title", v.author)}
                        view.displayListPosts(i) }
        }

        receivePosts(list)
    }


}
