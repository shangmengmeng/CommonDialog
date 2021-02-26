package com.star.libwidget.star_dialog.bottomPicker

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import androidx.fragment.app.FragmentManager
import com.star.libwidget.star_dialog.date.StarDateType.Companion.HM
import com.star.libwidget.star_dialog.date.StarDateType.Companion.HMS
import com.star.libwidget.star_dialog.date.StarDateType.Companion.YM
import com.star.libwidget.star_dialog.date.StarDateType.Companion.YMD
import com.star.libwidget.star_dialog.date.StarDateType.Companion.YMD_HMS
import com.star.libwidget.star_dialog.date.StarUnitType.Companion.DAY
import com.star.libwidget.star_dialog.date.StarUnitType.Companion.HOUR
import com.star.libwidget.star_dialog.date.StarUnitType.Companion.MINUTE
import com.star.libwidget.star_dialog.date.StarUnitType.Companion.MONTH
import com.star.libwidget.star_dialog.date.StarUnitType.Companion.SECOND
import com.star.libwidget.star_dialog.date.StarUnitType.Companion.YEAR
import java.util.*

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/7
 */
interface IStarBottomPicker {
    /**
     * 设置数据
     */
    fun<T> setData(list:List<T>):BottomPicker
    /**
     * 设置标题
     */
    fun setTitle(title: String): BottomPicker

    /**
     * 扩展属性设置
     */
    fun setSettingOption(option: BottomPickerSettingOption): BottomPicker

    /**
     * 设置监听
     */
    fun setOnSelectListener(listener: StarBottomPickerListener): BottomPicker
}

interface StarBottomPickerListener {
    /**
     * 确认
     * @param :日期
     */
    fun confirm(position: Int)

    /**
     * 取消
     */
    fun cancel()
}

interface IStarTypeName{
    /**
     *  名称用于展示列表
     */
    fun getName():String
}