package com.example.gleb.mediumme

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import com.example.gleb.mediumme.entities.PostEntityResponse
import com.example.gleb.mediumme.presenter.IListPostsPresenter
import com.example.gleb.mediumme.presenter.ListPostsPresenter
import com.example.gleb.mediumme.view.IListPostsView

class PostFragment: BaseFragment(), IListPostsView {
    val presenter: IListPostsPresenter = ListPostsPresenter(this)
    var postList: RecyclerView? = null
    var adapter: ListPostsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v: View = inflater!!.inflate(R.layout.fragment_post_list, container, false)

        postList = v.findViewById(R.id.posts_list) as RecyclerView?
        presenter.listPosts("10")
        return v
    }

    override fun displayListPosts(list: List<PostEntityResponse>) {
        var context = activity
        adapter = ListPostsAdapter(list, context)
        val llm = LinearLayoutManager(context)
        llm.setOrientation(LinearLayoutManager.VERTICAL)
        postList!!.layoutManager = llm
        postList!!.adapter = adapter
    }
}
