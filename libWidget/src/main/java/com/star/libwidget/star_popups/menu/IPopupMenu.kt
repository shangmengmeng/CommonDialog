package com.star.libwidget.star_popups.menu

import android.view.View

/**
 *  @des: 向外暴露的接口
 *  @author: sam
 *  @date: 2021/1/19
 */
interface IPopupMenu {
    /**
     *  view: the view on which to pin the popup window
     *  xOff: A horizontal offset from the view in pixels
     *  yOff: A vertical offset from the view in pixels
     */
    fun showAsDropDown(view: View, xOff: Int, yOff: Int): StarPopupMenu
    /**
     *  listener
     */
    fun setOnSelectListener(selectListener: StarPopupMenu.SelectListener): StarPopupMenu
    /**
     *  disMiss popupWindow
     */
    fun disMiss()
}