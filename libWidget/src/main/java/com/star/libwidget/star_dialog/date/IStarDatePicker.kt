package com.star.libwidget.star_dialog.date

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
 *  @date: 2021/2/3
 */
interface IStarDatePicker {

    /**
     * 设置标题
     */
    fun setTitle(title: String): StarDatePicker

    /**
     * 设置时间格式
     */
    fun setDateType(@StarDateType dateType: String): StarDatePicker

    /**
     * 带有单位
     */
    fun containUnit(containUnit: Boolean): StarDatePicker

    /**
     * 扩展属性设置
     */
    fun setSettingOption(option: DatePickerSettingOption): StarDatePicker

    /**
     * 设置监听
     */
    fun setOnSelectListener(listener: StarDatePickerListener): StarDatePicker
}

interface StarDatePickerListener {
    /**
     * 确认
     * @param dateStr:日期
     */
    fun confirm(dateStr: String, date: Date)

    /**
     * 取消
     */
    fun cancel()
}

@StringDef(YMD_HMS, YMD, YM, HMS, HM)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class StarDateType {
    companion object {
        const val YMD_HMS = "yyyy-MM-dd hh:mm:ss"  //年月日-时分秒
        const val YMD_HMS_TAB = "yyyy-MM-dd hh:mm:ss tab"
        const val YMD = "yy-MM-dd"                //年月日
        const val YM = "yyyy-MM"                  //年月
        const val HMS = "hh:mm:ss"                //时分秒
        const val HM = "hh:mm"                    //时分
        const val YMD_HM = "yyyy-MM-dd hh:mm"     //年月日时分
    }
}

@StringDef(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class StarUnitType {
    companion object {
        const val YEAR = "年"
        const val MONTH = "月"
        const val DAY = "日"
        const val HOUR = "时"
        const val MINUTE = "分"
        const val SECOND = "秒"
    }
}