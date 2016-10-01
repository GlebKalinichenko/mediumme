package com.example.gleb.mediumme

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnItemTouchListener
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.gleb.mediumme.entities.PostEntityResponse
import com.example.gleb.mediumme.event.ImageClickedEvent
import com.example.gleb.mediumme.helper.FragmentHelper
import com.example.gleb.mediumme.helper.NotificationHelper
import com.example.gleb.mediumme.helper.StringHelper
import com.example.gleb.mediumme.presenter.IListPostsPresenter
import com.example.gleb.mediumme.presenter.ListPostsPresenter
import com.example.gleb.mediumme.view.IListPostsView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class PostListFragment : BaseFragment(), IListPostsView {
    val LOG_TAG = this.javaClass.canonicalName
    val presenter: IListPostsPresenter = ListPostsPresenter(this)
    var postList: RecyclerView? = null
    var adapter: ListPostsAdapter? = null
    var addButton: FloatingActionButton? = null
    var coordinateLayout: CoordinatorLayout? = null
    var listEntities: List<PostEntityResponse>? = null
    var notificationHelper: NotificationHelper? = null
    var nm: NotificationManager? = null
    var stringHelper: StringHelper? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v: View = inflater!!.inflate(R.layout.fragment_post_list, container, false)

        initWidgets(v)
        presenter.listPosts("10")
        return v
    }

    val initNotificationIntent = { Intent(activity, MainActivity::class.java)}

    override fun initWidgets(v: View) {
        var context = activity
        postList = v.findViewById(R.id.posts_list) as RecyclerView?
        addButton = v.findViewById(R.id.fab) as FloatingActionButton?
        coordinateLayout = v.findViewById(R.id.main_content) as CoordinatorLayout?

        val llm = LinearLayoutManager(context)
        llm.setOrientation(LinearLayoutManager.VERTICAL)
        postList!!.layoutManager = llm

        initWidgetActions()

        postList!!.setHasFixedSize(true)
        postList!!.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    override fun initWidgetActions() {
        val context = activity
        val recyclerViewOnItemClickListener = RecyclerViewOnItemClickListener(context, this)
        postList!!.addOnItemTouchListener(recyclerViewOnItemClickListener)
        notificationHelper = NotificationHelper.instance
        nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        stringHelper = StringHelper.instance
    }

    override fun displayListPosts(list: List<PostEntityResponse>) {
        var context = activity
        listEntities = list
        adapter = ListPostsAdapter(list, context)
        postList!!.adapter = adapter
        EventBus().register(this)

        var title = stringHelper!!.getString(R.string.notification_new_post, context, 10.toString())

        notificationHelper!!.buildNotification(nm!!, context, title, "Text", initNotificationIntent)
    }

    fun openItemPost(entity: PostEntityResponse) {
        var fragment = PostItemFragment.getInstance(entity)
        FragmentHelper.reloadFragment(activity, R.id.layout_container, fragment)
    }

    override fun onItemClick(v: View, position: Int) {
        Log.d(LOG_TAG, "Clicked on position = $position")
        var entity = listEntities!!.get(position)
        openItemPost(entity)
    }

    /*Use event instead callback for clicked on post image*/
    @Subscribe
    fun onEvent(event: ImageClickedEvent){
        Log.d(LOG_TAG, "Post image was clicked with author ${event.item.author}")
    }
}
