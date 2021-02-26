package com.star.libwidget.star_popups.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.PopupWindow
import android.widget.Toast
import com.star.libwidget.R
import com.star.libwidget.utils.StarDisplayUtil

/**
 *  @des: 通用tip库
 *  @author: sam
 *  @date: 2021/1/19
 */
class StarCommonPopup(private var mContext: Context) : ICommonPopup {
    private var mText: String? = null
    private var mTextSize: Int = StarDisplayUtil.dp2px(mContext, 14)
    private var mTexColor: Int = Color.parseColor("#333333")
    private var mAnimationStyle: Int = R.style.StarPopupMenuAnimation
    private lateinit var mPopupWindow: PopupWindow
    private lateinit var mContentView: View

    /**
     * 设置文字
     */
    override fun setText(s: String): StarCommonPopup {
        mText = s
        return this
    }

    /**
     * 设置字体大小
     */
    override fun setTextSize(textSize: Int): StarCommonPopup {
        mTextSize = textSize
        return this
    }

    /**
     * 设置字体颜色
     */
    override fun setTextColor(textColor: Int): StarCommonPopup {
        mTexColor = textColor
        return this
    }

    /**
     * 设置动画样式
     */
    override fun setAnimationStyle(styleId: Int): StarCommonPopup {
        mAnimationStyle = styleId
        return this
    }

    /**
     * 显示
     *  view: the view on which to pin the popup window
     *  xOff: A horizontal offset from the view in pixels
     *  yOff: A vertical offset from the view in pixels
     */
    override fun show(view: View, xOff: Int, yOff: Int): StarCommonPopup {
        if (mText == null) {
            Toast.makeText(mContext, "没输入信息", Toast.LENGTH_SHORT).show()
        }
        initView()
        if (!mPopupWindow.isShowing) {
            mPopupWindow.showAsDropDown(view, xOff, yOff)
        }
        return this
    }

    /**
     *  取消
     */
    override fun dismiss() {
        if (mPopupWindow.isShowing) {
            mPopupWindow.dismiss()
        }
    }


    private fun initView() {
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.widget_layout_popup_dropdown, null)
        mContentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        initPopupWindow()
    }

    private fun initPopupWindow() {
        mPopupWindow = PopupWindow(mContext)
        mPopupWindow.contentView = mContentView
        mPopupWindow.height = WRAP_CONTENT
        mPopupWindow.width = WRAP_CONTENT
        mPopupWindow.isFocusable = true
        mPopupWindow.isOutsideTouchable = true
        mPopupWindow.setBackgroundDrawable(ColorDrawable())
        mPopupWindow.animationStyle = mAnimationStyle


    }
}