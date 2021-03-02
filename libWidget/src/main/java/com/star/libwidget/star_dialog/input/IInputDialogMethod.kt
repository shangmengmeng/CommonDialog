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
    fun setTitle(title: String): StarInputDialog

    /**
     * 确认文字
     */
    fun setConfirmText(confirmText: String): StarInputDialog

    /**
     * 取消文字
     */
    fun setCancelText(cancelText: String): StarInputDialog

    /**
     * 是否为单按钮
     */
    fun singleButton(isSingleButton: Boolean): StarInputDialog

    /**
     * 设置动画Style
     */
    fun setAnimStyle(@StyleRes styleId: Int): StarInputDialog

    /**
     * 监听
     */
    fun setOnStarInputDialogClickListener(dialogClickListener: StarInputDialogClickListener): StarInputDialog

    /**
     * 附加设置
     */
    fun setOptionSetting(option: InputDialogSettingOption): StarInputDialog
}

interface StarInputDialogClickListener {
    fun onConfirm(data: String)
    fun onCancel()
}