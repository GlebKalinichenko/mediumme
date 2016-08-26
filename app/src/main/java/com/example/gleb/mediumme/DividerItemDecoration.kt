package com.example.gleb.mediumme

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class DividerItemDecoration (context: Context, orientation: Int) : RecyclerView.ItemDecoration() {
    companion object {
        val ATTRS: IntArray = intArrayOf(android.R.attr.listDivider)
        val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST = LinearLayoutManager.VERTICAL

    }

    var mDivider: Drawable? = null;
    var mOrientation: Int = 0;

    init {
        val a: TypedArray = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
        setOrientation(orientation)
    }

    fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    fun drawVertical(c: Canvas?, parent: RecyclerView?) {
        var left: Int = parent!!.paddingLeft
        var right: Int = parent.width - parent.right
        var childCount: Int = parent.childCount

        for (i in 0..childCount) {
            if (parent.getChildAt(i) != null) {
                val child: View = parent.getChildAt(i)
                val params: RecyclerView.LayoutParams = child.layoutParams as (RecyclerView.LayoutParams)
                val top: Int = child.bottom + params.bottomMargin
                val bottom: Int = top + mDivider!!.intrinsicHeight
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(c)
            }
        }
    }

    fun drawHorizontal(c: Canvas?, parent: RecyclerView?) {
        val top: Int = parent!!.paddingTop
        val bottom: Int = parent.height - parent.paddingBottom

        val childCount: Int = parent.childCount
        for (i in 0..childCount) {
            if (parent.getChildAt(i) != null) {
                val child: View = parent.getChildAt(i)
                val params: RecyclerView.LayoutParams = child.layoutParams as (RecyclerView.LayoutParams)
                val left: Int = child.right + params.rightMargin
                val right: Int = left + mDivider!!.intrinsicHeight
                mDivider!!.setBounds(left, top, right, bottom);
                mDivider!!.draw(c);
            }
        }
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (mOrientation == VERTICAL_LIST) {
            outRect!!.set(0, 0, 0, mDivider!!.intrinsicHeight);
        } else {
            outRect!!.set(0, 0, mDivider!!.intrinsicWidth, 0);
        }
    }
}