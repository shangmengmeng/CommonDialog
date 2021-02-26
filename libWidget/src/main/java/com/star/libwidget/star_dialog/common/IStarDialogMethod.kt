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
    fun setTitle(title: String): StarDialog.Builder

    /**
     * 设置内容
     */
    fun setContent(content: String): StarDialog.Builder

    /**
     * 确认文字
     */
    fun setConfirmText(confirmText: String): StarDialog.Builder

    /**
     * 取消文字
     */
    fun setCancelText(cancelText: String): StarDialog.Builder

    /**
     * 是否为单按钮
     */
    fun single(isSingleButton: Boolean): StarDialog.Builder

    /**
     * 设置动画Style
     */
    fun setAnimStyle(@StyleRes styleId: Int): StarDialog.Builder

    /**
     * 扩展属性设置
     */
    fun setOptionSetting(option: DialogSettingOption): StarDialog.Builder

    /**
     * 监听
     */
    fun setOnStarDialogClickListener(dialogClickListener: StarDialogClickListener): StarDialog.Builder

    /**
     * 展示
     */
    fun show(manager: FragmentManager)
}

interface StarDialogClickListener {
    fun onConfirm()
    fun onCancel()
}