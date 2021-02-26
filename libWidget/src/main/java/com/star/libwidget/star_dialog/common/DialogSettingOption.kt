package com.star.libwidget.star_dialog.common

/**
 *  @des: 扩展类
 *  @author: sam
 *  @date: 2021/2/1
 */
class DialogSettingOption : IDialogSettingOption {
    var titleColor: Int? = null
    var titleSize: Float? = null
    var contentColor: Int? = null
    var contentSize: Float? = null
    var confirmTextColor: Int? = null
    var confirmTextSize: Float? = null
    var cancelTextColor: Int? = null
    var cancelTextSize: Float? = null
    var dividerColor: Int? = null

    override fun setTitleColor(color: Int): DialogSettingOption {
        this.titleColor = color
        return this
    }

    override fun setTitleSize(size: Float): DialogSettingOption {
        this.titleSize = size
        return this
    }

    override fun setContentColor(color: Int): DialogSettingOption {
        this.contentColor = color
        return this
    }

    override fun setContentSize(size: Float): DialogSettingOption {
        this.contentSize = size
        return this
    }

    override fun setConfirmTextColor(color: Int): DialogSettingOption {
        this.confirmTextColor = color
        return this
    }

    override fun setConfirmTextSize(size: Float): DialogSettingOption {
        this.confirmTextSize = size
        return this
    }

    override fun setCancelTextColor(color: Int): DialogSettingOption {
        this.cancelTextColor = color
        return this
    }

    override fun setCancelTextSize(size: Float): DialogSettingOption {
        this.cancelTextSize = size
        return this
    }

    override fun setDividerColor(color: Int): DialogSettingOption {
        this.dividerColor = color
        return this
    }

}