package com.star.libwidget.star_dialog.input

import androidx.annotation.ColorInt

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/1
 */
interface IInputDialogSettingOption {
    /**
     * 标题颜色
     */
    fun setTitleColor(@ColorInt color: Int): InputDialogSettingOption

    /**
     * 标题大小
     */
    fun setTitleSize(size: Float): InputDialogSettingOption

    /**
     * 内容颜色
     */
    fun setContentColor(@ColorInt color: Int): InputDialogSettingOption

    /**
     * 内容大小
     */
    fun setContentSize(size: Float): InputDialogSettingOption

    /**
     * 确定按钮字体颜色
     */
    fun setConfirmTextColor(@ColorInt color: Int): InputDialogSettingOption

    /**
     * 确定按钮字体大小
     */
    fun setConfirmTextSize(float: Float): InputDialogSettingOption

    /**
     * 取消按钮字体颜色
     */
    fun setCancelTextColor(@ColorInt color: Int): InputDialogSettingOption

    /**
     * 取消按钮字体大小
     */
    fun setCancelTextSize(size: Float): InputDialogSettingOption

    /**
     * 分割线颜色
     */
    fun setDividerColor(@ColorInt color: Int): InputDialogSettingOption
}