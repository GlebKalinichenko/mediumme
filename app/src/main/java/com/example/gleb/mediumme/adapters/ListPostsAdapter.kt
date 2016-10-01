package com.example.gleb.mediumme.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gleb.mediumme.R
import com.example.gleb.mediumme.entities.PostEntityResponse
import com.squareup.picasso.Picasso
import org.greenrobot.eventbus.EventBus

class ListPostsAdapter (var lists: List<PostEntityResponse>, val context: Context): RecyclerView.Adapter<ListPostsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.textAuthor!!.text = lists.get(position).author
        holder!!.textTitle!!.text = lists.get(position).title
        var url = lists.get(position).thumbnail
        if (!url.equals("")) {
            //ImageHelper.loadImage(context, holder.postImage, url)
            holder.postImage!!.load(url)
            initPostImageEvent(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent!!.context).inflate(R.layout.adapter_post_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    fun initPostImageEvent(position: Int){
        var item = lists.get(position)
        EventBus().post(item)
    }

    class ViewHolder (var view: View) : RecyclerView.ViewHolder(view) {
        var textAuthor: TextView? = null
        var textTitle: TextView? = null
        var postImage: ImageView? = null

        init {
            this.view = view
            textAuthor = view.findViewById(R.id.text_author) as TextView
            textTitle = view.findViewById(R.id.text_title) as TextView
            postImage = view.findViewById(R.id.post_image) as ImageView
        }
    }

    fun ImageView.load(url: String){
        Picasso.with(context).load(url).into(this)
    }
}