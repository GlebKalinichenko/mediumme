package com.example.gleb.mediumme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gleb.mediumme.entities.PostEntityResponse
import com.example.gleb.mediumme.presenter.IListPostsPresenter
import com.example.gleb.mediumme.presenter.ListPostsPresenter
import com.example.gleb.mediumme.view.IListPostsView

class PostFragment: BaseFragment(), IListPostsView {
    val presenter: IListPostsPresenter = ListPostsPresenter(this)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v: View = inflater!!.inflate(R.layout.fragment_base, container, false)

        presenter.listPosts("10")
        return v
    }

    override fun displayListPosts(list: List<PostEntityResponse>) {

    }
}
