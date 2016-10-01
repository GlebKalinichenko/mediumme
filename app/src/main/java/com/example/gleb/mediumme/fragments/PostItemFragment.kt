package com.example.gleb.mediumme.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.gleb.mediumme.PostListBottomSheetCallback
import com.example.gleb.mediumme.R
import com.example.gleb.mediumme.entities.PostEntityResponse
import com.example.gleb.mediumme.fragments.BaseFragment
import com.example.gleb.mediumme.presenter.IPostDetailsPresenter
import com.example.gleb.mediumme.presenter.PostDetailsPresenter
import com.example.gleb.mediumme.view.IPostDetailsView
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso

class PostItemFragment: BaseFragment(), IPostDetailsView {
    val LOG_TAG = this.javaClass.canonicalName
    var postImageView: ImageView? = null
    var entity: PostEntityResponse? = null
    val detailsPresenter: IPostDetailsPresenter = PostDetailsPresenter(this)
    var googleClient: GoogleApiClient? = null

    companion object {
        val POST_ITEM_FRAGMENT_ENTITY = "PostItemFragmentEntity"

        fun getInstance(entity: PostEntityResponse): PostItemFragment {
            var bundle: Bundle = Bundle()
            bundle.putSerializable(POST_ITEM_FRAGMENT_ENTITY, entity)

            var fragment: PostItemFragment = PostItemFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        googleClient = GoogleApiClient.Builder(context).addApi(LocationServices.API).build()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        entity = arguments.getSerializable(POST_ITEM_FRAGMENT_ENTITY) as PostEntityResponse
        val v = inflater!!.inflate(R.layout.fragment_post_item_details, container, false)

        initWidgets(v, displayPostImage)
        initPostComments(v)

        return v
    }

//    fun initLocationRequest(): LocationRequest {
//        var lRequest : LocationRequest = LocationRequest.create()
//        lRequest.interval = (10 * 1000).toLong()
//        lRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//
//        return lRequest
//    }

    override fun onStart() {
        super.onStart()
        detailsPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        var context: Context = activity
        detailsPresenter.onResume(context)
    }

    override fun onStop() {
        detailsPresenter.onStop()
        super.onStop()
    }

    override fun onPause() {
        detailsPresenter.onPause()
        super.onPause()
    }

    override fun initWidgets(v: View, displayImage: () -> Unit) {
        postImageView = v.findViewById(R.id.post_image_details) as ImageView?
    }


    fun initPostComments(v: View) {
        var layoutThumbnails : LinearLayout = v.findViewById(R.id.layout_comment) as LinearLayout
        var behaviorPostThubnails = BottomSheetBehavior.from(layoutThumbnails)

//        behaviorPostThubnails.peekHeight = 340
//        behaviorPostThubnails.state = BottomSheetBehavior.STATE_COLLAPSED
//        behaviorPostThubnails.state = BottomSheetBehavior.STATE_EXPANDED
//        behaviorPostThubnails.state = BottomSheetBehavior.STATE_HIDDEN
//        behaviorPostThubnails.setHideable(false)

        behaviorPostThubnails.setBottomSheetCallback(PostListBottomSheetCallback())
    }

    val displayPostImage = { val context = activity; val url = entity!!.url; postImageView!!.loadImage(url, context) }

    fun ImageView.loadImage(url: String, context: Context){
        Picasso.with(context).load(url).into(this)
    }

}