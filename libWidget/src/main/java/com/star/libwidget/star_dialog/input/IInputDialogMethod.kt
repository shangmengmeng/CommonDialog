package com.star.libwidget.star_dialog.input

import androidx.annotation.StyleRes

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/1
 */
interface IInputStarDialogMethod {
    /**
     * 设置标题
     */
    fun setTitle(title: String): StarInputDialog.Builder

    /**
     * 确认文字
     */
    fun setConfirmText(confirmText: String): StarInputDialog.Builder

    /**
     * 取消文字
     */
    fun setCancelText(cancelText: String): StarInputDialog.Builder

    /**
     * 是否为单按钮
     */
    fun singleButton(isSingleButton: Boolean): StarInputDialog.Builder

    /**
     * 设置动画Style
     */
    fun setAnimStyle(@StyleRes styleId: Int): StarInputDialog.Builder

    /**
     * 监听
     */
    fun setOnStarInputDialogClickListener(dialogClickListener: StarInputDialogClickListener): StarInputDialog.Builder

    /**
     * 附加设置
     */
    fun setOptionSetting(option: InputDialogSettingOption): StarInputDialog.Builder
}

interface StarInputDialogClickListener {
    fun onConfirm(data: String)
    fun onCancel()
}