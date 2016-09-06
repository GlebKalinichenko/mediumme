package com.example.gleb.mediumme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.gleb.mediumme.entities.PostEntityResponse
import com.example.gleb.mediumme.view.IPostDetailsView
import com.squareup.picasso.Picasso

class PostItemFragment: BaseFragment(), IPostDetailsView {
    val LOG_TAG = this.javaClass.canonicalName
    var postImageView: ImageView? = nullsma
    var entity: PostEntityResponse? = null

    companion object {
        val POST_ITEM_FRAGMENT_ENTITY = "PostItemFragmentEntity"

        fun getInstance(entity: PostEntityResponse): PostItemFragment{
            var bundle: Bundle = Bundle()
            bundle.putSerializable(POST_ITEM_FRAGMENT_ENTITY, entity)

            var fragment: PostItemFragment = PostItemFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        entity = arguments.getSerializable(POST_ITEM_FRAGMENT_ENTITY) as PostEntityResponse
        val v = inflater!!.inflate(R.layout.fragment_post_item_details, container, false)

        initWidgets(v, displayPostImage)
        displayPostImage()

        return v
    }

    override fun initWidgets(v: View, displayImage: () -> Unit) {
        postImageView = v.findViewById(R.id.post_image_details) as ImageView?
    }

    val displayPostImage = { val context = activity; val url = entity!!.url; postImageView!!.loadImage(url, context) }

    fun ImageView.loadImage(url: String, context: Context){
        Picasso.with(context).load(url).into(this)
    }

}