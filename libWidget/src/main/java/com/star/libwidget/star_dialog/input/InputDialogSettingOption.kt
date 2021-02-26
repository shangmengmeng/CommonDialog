package com.star.libwidget.star_dialog.input

/**
 *  @des: 扩展类
 *  @author: sam
 *  @date: 2021/2/1
 */
class InputDialogSettingOption : IInputDialogSettingOption {
    var titleColor: Int? = null
    var titleSize: Float? = null
    var contentColor: Int? = null
    var contentSize: Float? = null
    var confirmTextColor: Int? = null
    var confirmTextSize: Float? = null
    var cancelTextColor: Int? = null
    var cancelTextSize: Float? = null
    var dividerColor: Int? = null

    override fun setTitleColor(color: Int): InputDialogSettingOption {
        this.titleColor = color
        return this
    }

    override fun setTitleSize(size: Float): InputDialogSettingOption {
        this.titleSize = size
        return this
    }

    override fun setContentColor(color: Int): InputDialogSettingOption {
        this.contentColor = color
        return this
    }

    override fun setContentSize(size: Float): InputDialogSettingOption {
        this.contentSize = size
        return this
    }

    override fun setConfirmTextColor(color: Int): InputDialogSettingOption {
        this.confirmTextColor = color
        return this
    }

    override fun setConfirmTextSize(size: Float): InputDialogSettingOption {
        this.confirmTextSize = size
        return this
    }

    override fun setCancelTextColor(color: Int): InputDialogSettingOption {
        this.cancelTextColor = color
        return this
    }

    override fun setCancelTextSize(size: Float): InputDialogSettingOption {
        this.cancelTextSize = size
        return this
    }

    override fun setDividerColor(color: Int): InputDialogSettingOption {
        this.dividerColor = color
        return this
    }

}