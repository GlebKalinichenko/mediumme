package com.example.gleb.mediumme

import android.content.Context
import android.gesture.Gesture
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class RecyclerViewOnItemClickListener(context: Context, ls: OnItemPostClickListener) : RecyclerView.OnItemTouchListener, GestureDetector.SimpleOnGestureListener() {
    var ls: OnItemPostClickListener? = null
    var gs: GestureDetector? = null
    var childView: View? = null
    var childPosition: Int? = null

    init {
        this.ls = ls
        gs = GestureDetector(context, this)
    }

    //listener
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        childView.let { ls!!.onItemClick(childView!!, childPosition!!); return true }
    }

    interface OnItemPostClickListener{
        fun onItemClick(v: View, position: Int)
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {

    }

    //handler on touch
    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
//        var childView: View = rv!!.findChildViewUnder(e!!.x, e!!.y)
//        if (childView != null && ls != null && gs!!.onTouchEvent(e)){
//            ls!!.onItemClick(childView, rv.getChildAdapterPosition(childView))
//        }

        childView = rv!!.findChildViewUnder(e!!.x, e!!.y)
        childPosition = rv.getChildAdapterPosition(childView)

        return childView != null && gs!!.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

}