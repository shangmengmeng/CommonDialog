package com.star.libwidget.star_dialog.common

import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentManager

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/1
 */
interface IStarDialogMethod {
    /**
     * 设置标题
     */
    fun setTitle(title: String): StarDialog

    /**
     * 设置内容
     */
    fun setContent(content: String): StarDialog

    /**
     * 确认文字
     */
    fun setConfirmText(confirmText: String): StarDialog

    /**
     * 取消文字
     */
    fun setCancelText(cancelText: String): StarDialog

    /**
     * 是否为单按钮
     */
    fun single(isSingleButton: Boolean): StarDialog

    /**
     * 设置动画Style
     */
    fun setAnimStyle(@StyleRes styleId: Int): StarDialog

    /**
     * 扩展属性设置
     */
    fun setOptionSetting(option: DialogSettingOption): StarDialog

    /**
     * 监听
     */
    fun setOnStarDialogClickListener(dialogClickListener: StarDialogClickListener): StarDialog

    /**
     * 展示
     */
    fun show()

}

interface StarDialogClickListener {
    fun onConfirm()
    fun onCancel()
}