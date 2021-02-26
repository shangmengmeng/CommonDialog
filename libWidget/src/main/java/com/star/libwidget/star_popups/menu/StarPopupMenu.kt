package com.star.libwidget.star_popups.menu

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.star.libwidget.R
import com.star.libwidget.star_popups.menu.PopupAdapter.*
import java.io.Serializable

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/1/19
 */
class StarPopupMenu(private var mActivity: Activity, private var mDataList: List<ItemBean>) :
    IPopupMenu {
    companion object {
        val DEFAULT_ANIM_STYLE = R.style.StarPopupMenuAnimation
    }

    private lateinit var mPopupWindow: PopupWindow
    private lateinit var mContentView: View

    init {
        initView()
    }

    private fun initView() {
        mContentView = LayoutInflater.from(mActivity).inflate(R.layout.widget_layout_popup_dropdown, null)
        //父View不干涉子View大小
        mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        initRecyclerView()
        initPopupWindow()
    }

    /**
     *  加载 PopupWindow
     */
    private fun initPopupWindow() {
        mPopupWindow = PopupWindow(mActivity)
        mPopupWindow.contentView = mContentView
        mPopupWindow.height = WRAP_CONTENT
        mPopupWindow.width = WRAP_CONTENT
        mPopupWindow.isFocusable = true
        mPopupWindow.isOutsideTouchable = true
        mPopupWindow.setBackgroundDrawable(ColorDrawable())
        //设置动画
        mPopupWindow.animationStyle = DEFAULT_ANIM_STYLE
    }

    /**
     *  加载 RecyclerView
     */
    private fun initRecyclerView() {
        val recyclerView = mContentView.findViewById<RecyclerView>(R.id.rv_popups_dropdown)
        val linearLayoutManager = LinearLayoutManager(mActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        val adapter = PopupAdapter(mDataList, mActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ItemClickListener {
            override fun onItemClickListener(position: Int, name: String) {
                selectListener?.onSelectListener(position, name)
            }
        })
    }

    /**
     *  view: the view on which to pin the popup window
     *  xOff: A horizontal offset from the view in pixels
     *  yOff: A vertical offset from the view in pixels
     */
    override fun showAsDropDown(view: View, xOff: Int, yOff: Int): StarPopupMenu {
        if (!mPopupWindow.isShowing) {
            mPopupWindow.showAsDropDown(view, xOff, yOff)
        }
        return this
    }

    /**
     *  disMiss popupWindow
     */
    override fun disMiss() {
        if (mPopupWindow.isShowing) {
            mPopupWindow.dismiss()
        }
    }

    /**
     *  listener
     */
    override fun setOnSelectListener(selectListener: SelectListener): StarPopupMenu {
        this.selectListener = selectListener
        return this
    }

    class ItemBean(var iconId: Int, var name: String) : Serializable

    interface SelectListener {
        fun onSelectListener(position: Int, name: String)
    }

    private var selectListener: SelectListener? = null

}