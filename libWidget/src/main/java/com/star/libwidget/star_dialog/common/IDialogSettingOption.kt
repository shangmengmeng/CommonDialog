package com.star.libwidget.star_dialog.common

import androidx.annotation.ColorInt

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/1
 */
interface IDialogSettingOption {
    /**
     * 标题颜色
     */
    fun setTitleColor(@ColorInt color: Int): DialogSettingOption

    /**
     * 标题大小
     */
    fun setTitleSize(size: Float): DialogSettingOption

    /**
     * 内容颜色
     */
    fun setContentColor(@ColorInt color: Int): DialogSettingOption

    /**
     * 内容大小
     */
    fun setContentSize(size: Float): DialogSettingOption

    /**
     * 确定按钮字体颜色
     */
    fun setConfirmTextColor(@ColorInt color: Int): DialogSettingOption

    /**
     * 确定按钮字体大小
     */
    fun setConfirmTextSize(float: Float): DialogSettingOption

    /**
     * 取消按钮字体颜色
     */
    fun setCancelTextColor(@ColorInt color: Int): DialogSettingOption

    /**
     * 取消按钮字体大小
     */
    fun setCancelTextSize(float: Float): DialogSettingOption

    /**
     * 分割线颜色
     */
    fun setDividerColor(@ColorInt color: Int): DialogSettingOption
}