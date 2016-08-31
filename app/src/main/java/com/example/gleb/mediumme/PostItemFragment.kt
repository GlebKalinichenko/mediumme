package com.example.gleb.mediumme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gleb.mediumme.entities.PostEntityResponse

class PostItemFragment: BaseFragment(){


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
        val v = inflater!!.inflate(R.layout.fragment_post_item, container, false)

        return v
    }

}