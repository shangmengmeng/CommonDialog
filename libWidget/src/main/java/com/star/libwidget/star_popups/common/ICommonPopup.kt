package com.star.libwidget.star_popups.common

import android.view.View
import androidx.annotation.StyleRes

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/1/19
 */
interface ICommonPopup {

    /**
     * 设置文字
     */
    fun setText(s: String): StarCommonPopup

    /**
     * 设置字体大小
     */
    fun setTextSize(textSize: Int): StarCommonPopup

    /**
     * 设置字体颜色
     */
    fun setTextColor(textColor: Int): StarCommonPopup

    /**
     * 设置动画样式
     */
    fun setAnimationStyle(@StyleRes styleId: Int): StarCommonPopup

    /**
     * 显示
     *  view: the view on which to pin the popup window
     *  xOff: A horizontal offset from the view in pixels
     *  yOff: A vertical offset from the view in pixels
     */
    fun show(view: View, xOff: Int, yOff: Int): StarCommonPopup

    /**
     *  取消
     */
    fun dismiss()
}