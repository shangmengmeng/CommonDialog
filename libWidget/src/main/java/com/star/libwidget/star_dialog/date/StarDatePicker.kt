package com.star.libwidget.star_dialog.date

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.google.android.material.tabs.TabLayout
import com.star.libwidget.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/3
 */
class StarDatePicker : DialogFragment(), IStarDatePicker {
    //接收的参数
    private var mDateType = StarDateType.YMD
    private var mContainUnit = true
    private var dayAdapter: StarDatePickerAdapter? = null
    private var yearLayoutManager: LinearLayoutManager? = null
    private var monthLayoutManager: LinearLayoutManager? = null
    private var dayLayoutManager: LinearLayoutManager? = null
    private var hourLayoutManager: LinearLayoutManager? = null
    private var minuteLayoutManager: LinearLayoutManager? = null
    private var secondLayoutManager: LinearLayoutManager? = null
    private var mMinYear = 1900
    private var mMaxYear = 2300
    private var mAnimStyleId: Int? = null
    private lateinit var mDefaultDate: Date
    private lateinit var mCalendar: Calendar
    private lateinit var mRvYear: RecyclerView
    private lateinit var mRvMonth: RecyclerView
    private lateinit var mRvDay: RecyclerView
    private lateinit var mRvHour: RecyclerView
    private lateinit var mRvMinute: RecyclerView
    private lateinit var mRvSecond: RecyclerView
    private lateinit var mBtnCancel: TextView
    private lateinit var mBtnConfirm: TextView
    private lateinit var mTabLayout: TabLayout
    private var mYearList = ArrayList<String>()
    private var mMonthList = ArrayList<String>()
    private var mDayList = ArrayList<String>()
    private var mHourList = ArrayList<String>()
    private var mMinList = ArrayList<String>()
    private var mSecondList = ArrayList<String>()

    private var mCurrentYear: Int = 0
    private var mCurrentMonth: Int = 0
    private var mStarDatePickerListener: StarDatePickerListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.widget_layout_picker_date)
        findView(dialog)
        initDialog(dialog)
        initDateList()
        initDateChangeListener()
        initClickListener()
        return dialog
    }

    private fun findView(dialog: Dialog) {
        mRvYear = dialog.findViewById(R.id.widget_rv_y)
        mRvMonth = dialog.findViewById(R.id.widget_rv_mon)
        mRvDay = dialog.findViewById(R.id.widget_rv_d)
        mRvHour = dialog.findViewById(R.id.widget_rv_h)
        mRvMinute = dialog.findViewById(R.id.widget_rv_m)
        mRvSecond = dialog.findViewById(R.id.widget_rv_s)
        mBtnCancel = dialog.findViewById(R.id.widget_tv_picker_cancel)
        mBtnConfirm = dialog.findViewById(R.id.widget_tv_picker_confirm)
        mTabLayout = dialog.findViewById(R.id.widget_tb_dialog)
        when (mDateType) {
            //年月日时分秒
            StarDateType.YMD_HMS -> {
                mRvYear.visibility = View.VISIBLE
                mRvMonth.visibility = View.VISIBLE
                mRvDay.visibility = View.VISIBLE
                mRvHour.visibility = View.VISIBLE
                mRvMinute.visibility = View.VISIBLE
                mRvSecond.visibility = View.VISIBLE
            }
            //年月日时分秒
            StarDateType.YMD_HMS_TAB -> {
                mRvYear.visibility = View.VISIBLE
                mRvMonth.visibility = View.VISIBLE
                mRvDay.visibility = View.VISIBLE
                mTabLayout.visibility = View.VISIBLE
            }
            //年月日
            StarDateType.YMD -> {
                mRvYear.visibility = View.VISIBLE
                mRvMonth.visibility = View.VISIBLE
                mRvDay.visibility = View.VISIBLE
            }
            //年月
            StarDateType.YM -> {
                mRvYear.visibility = View.VISIBLE
                mRvMonth.visibility = View.VISIBLE
            }
            //时分秒
            StarDateType.HMS -> {
                mRvHour.visibility = View.VISIBLE
                mRvMinute.visibility = View.VISIBLE
                mRvSecond.visibility = View.VISIBLE
            }
            //时分
            StarDateType.HM -> {
                mRvHour.visibility = View.VISIBLE
                mRvMinute.visibility = View.VISIBLE
            }
            else -> {
                throw java.lang.Exception("日期类型不匹配")
            }
        }
    }

    private fun initDialog(dialog: Dialog) {
        // 设置宽度为屏宽, 靠近屏幕底部。
        val window = dialog.window
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (mAnimStyleId != null && mAnimStyleId != 0) {
            window.setWindowAnimations(mAnimStyleId!!)
        } else {
            window.setWindowAnimations(R.style.StarDialogStyle3)
        }
        val lp: WindowManager.LayoutParams = window.attributes
        lp.gravity = Gravity.BOTTOM
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        window.attributes = lp
    }

    private fun initDateList() {
        mDefaultDate = Date()
        mCalendar = Calendar.getInstance()
        mCalendar.time = mDefaultDate
        when (mDateType) {
            //年月日时分秒
            StarDateType.YMD_HMS -> {
                initYearListData()
                initMonthListData()
                initDayListData()
                initHourListData()
                initMinuteListData()
                initSecondListData()
            }
            StarDateType.YMD_HMS_TAB -> {
                initYearListData()
                initMonthListData()
                initDayListData()
                initHourListData()
                initMinuteListData()
                initSecondListData()
                val df = DecimalFormat("00")
                df.format(mCalendar[Calendar.HOUR_OF_DAY]) + "时" +
                        df.format(mCalendar[Calendar.MINUTE]) + "分" +
                        df.format(mCalendar[Calendar.SECOND]) + "秒"
                mTabLayout.addTab(
                    mTabLayout.newTab()
                        .setText(
                            df.format(mCalendar[Calendar.YEAR]) + "年" + df.format(mCurrentMonth) + "月" + df.format(
                                mCalendar[Calendar.DAY_OF_MONTH]
                            ) + "日"
                        )
                )
                mTabLayout.addTab(
                    mTabLayout.newTab()
                        .setText(
                            df.format(mCalendar[Calendar.HOUR_OF_DAY]) + "时" + df.format(
                                mCalendar[Calendar.MINUTE]
                            ) + "分" + df.format(mCalendar[Calendar.SECOND]) + "秒"
                        )
                )
            }
            //年月日
            StarDateType.YMD -> {
                initYearListData()
                initMonthListData()
                initDayListData()
            }
            //年月
            StarDateType.YM -> {
                initYearListData()
                initMonthListData()
            }
            //时分秒
            StarDateType.HMS -> {
                initHourListData()
                initMinuteListData()
                initSecondListData()
            }
            //时分
            StarDateType.HM -> {
                initHourListData()
                initMinuteListData()
            }
            else -> {
                throw java.lang.Exception("日期类型不匹配")
            }
        }
    }

    /**
     * 年的设置
     */
    private fun initYearListData() {
        mYearList.clear()
        mYearList.add("")
        mYearList.add("")
        for (index in mMinYear..mMaxYear) {
            mYearList.add(index.toString())
        }
        yearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        mYearList.add("")
        mYearList.add("")
        val yearSnapHelper = LinearSnapHelper()
        val yearAdapter =
            StarDatePickerAdapter(context!!, mYearList, mContainUnit, StarUnitType.YEAR, 0, null)
        mRvYear.layoutManager = yearLayoutManager
        mRvYear.adapter = yearAdapter
        yearSnapHelper.attachToRecyclerView(mRvYear)
        mCurrentYear = mCalendar[Calendar.YEAR]
        yearLayoutManager?.scrollToPositionWithOffset(mCurrentYear - mMinYear, 0)
    }

    /**
     * 月设置
     */
    private fun initMonthListData() {
        mMonthList.clear()
        mMonthList.add("")
        mMonthList.add("")
        for (i in 1 until 13) {
            if (i < 10) {
                mMonthList.add("0$i")
            } else {
                mMonthList.add(i.toString())
            }
        }
        mMonthList.add("")
        mMonthList.add("")
        monthLayoutManager = LinearLayoutManager(context)
        val monthSnapHelper = LinearSnapHelper()
        val monthAdapter =
            StarDatePickerAdapter(context!!, mMonthList, mContainUnit, StarUnitType.MONTH, 0, null)
        monthSnapHelper.attachToRecyclerView(mRvMonth)
        mRvMonth.layoutManager = monthLayoutManager
        mRvMonth.adapter = monthAdapter
        mCurrentMonth = mCalendar[Calendar.MONTH] + 1
        monthLayoutManager?.scrollToPositionWithOffset(mCurrentMonth - 1, 0)
    }

    /**
     * 日设置
     */
    private fun initDayListData() {
        //日设置
        refreshDayList()
        dayLayoutManager = LinearLayoutManager(context)
        val daySnapHelper = LinearSnapHelper()
        dayAdapter =
            StarDatePickerAdapter(context!!, mDayList, mContainUnit, StarUnitType.DAY, 0, null)
        daySnapHelper.attachToRecyclerView(mRvDay)
        mRvDay.layoutManager = dayLayoutManager
        mRvDay.adapter = dayAdapter
        val day = mCalendar[Calendar.DAY_OF_MONTH]
        dayLayoutManager?.scrollToPositionWithOffset(day - 1, 0)
    }

    /**
     * 时设置
     */
    private fun initHourListData() {
        mHourList = ArrayList()
        mHourList.clear()
        mHourList.add("")
        mHourList.add("")
        for (i in 0..23) {
            if (i < 10) {
                mHourList.add("0$i")
            } else {
                mHourList.add(i.toString())
            }
        }
        mHourList.add("")
        mHourList.add("")

        hourLayoutManager = LinearLayoutManager(context)
        val hourSnapHelper = LinearSnapHelper()
        val hourAdapter =
            StarDatePickerAdapter(context!!, mHourList, mContainUnit, StarUnitType.HOUR, 0, null)
        hourSnapHelper.attachToRecyclerView(mRvHour)
        mRvHour.layoutManager = hourLayoutManager
        mRvHour.adapter = hourAdapter
        val currentHour = mCalendar[Calendar.HOUR_OF_DAY]
        hourLayoutManager?.scrollToPositionWithOffset(currentHour, 0)
    }

    /**
     * 分设置
     */
    private fun initMinuteListData() {
        mMinList = ArrayList()
        mMinList.clear()
        mMinList.add("")
        mMinList.add("")
        for (i in 0..59) {
            if (i < 10) {
                mMinList.add("0$i")
            } else {
                mMinList.add(i.toString())
            }
        }
        mMinList.add("")
        mMinList.add("")

        minuteLayoutManager = LinearLayoutManager(context)
        val minSnapHelper = LinearSnapHelper()
        val minAdapter =
            StarDatePickerAdapter(context!!, mMinList, mContainUnit, StarUnitType.MINUTE, 0, null)
        minSnapHelper.attachToRecyclerView(mRvMinute)
        mRvMinute.layoutManager = minuteLayoutManager
        mRvMinute.adapter = minAdapter
        val currentMinute = mCalendar[Calendar.MINUTE]
        minuteLayoutManager?.scrollToPositionWithOffset(currentMinute, 0)
    }

    /**
     * 秒设置
     */
    private fun initSecondListData() {
        mSecondList = ArrayList()
        mSecondList.clear()
        mSecondList.add("")
        mSecondList.add("")
        for (i in 0..59) {
            if (i < 10) {
                mSecondList.add("0$i")
            } else {
                mSecondList.add(i.toString())
            }
        }
        mSecondList.add("")
        mSecondList.add("")

        secondLayoutManager = LinearLayoutManager(context)
        val secondSnapHelper = LinearSnapHelper()
        val minAdapter =
            StarDatePickerAdapter(context!!, mMinList, mContainUnit, StarUnitType.SECOND, 0, null)
        secondSnapHelper.attachToRecyclerView(mRvSecond)
        mRvSecond.layoutManager = secondLayoutManager
        mRvSecond.adapter = minAdapter
        val currentSecond = mCalendar[Calendar.SECOND]
        secondLayoutManager?.scrollToPositionWithOffset(currentSecond, 0)
    }

    private fun refreshDayList() {
        val dayCount: Int
        when (mCurrentMonth) {
            1, 3, 5, 7, 8, 10, 12 -> {
                dayCount = 31
            }
            2 -> {
                // 闰年
                val isLeapYear =
                    mCurrentYear % 4 == 0 && mCurrentYear % 100 != 0 || mCurrentYear % 400 == 0
                //闰年
                dayCount = if (!isLeapYear) {
                    28
                } else {
                    29
                }
            }
            else -> {
                dayCount = 30
            }
        }

        mDayList.clear()
        mDayList.add("")
        mDayList.add("")
        for (index in 1..dayCount) {
            if (index < 10) {
                mDayList.add("0$index")
            } else {
                mDayList.add(index.toString())
            }
        }
        mDayList.add("")
        mDayList.add("")
        dayAdapter?.notifyDataSetChanged()
    }

    private fun initDateChangeListener() {
        mRvYear.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    mCurrentYear =
                        mMinYear + yearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                    refreshDayList()
                    if (mDateType == StarDateType.YMD_HMS_TAB) {
                        mTabLayout.getTabAt(0)?.text = getYmd()
                    }
                }
            }
        })
        mRvMonth.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    mCurrentMonth =
                        monthLayoutManager!!.findFirstCompletelyVisibleItemPosition() + 1
                    refreshDayList()
                    if (mDateType == StarDateType.YMD_HMS_TAB) {
                        mTabLayout.getTabAt(0)?.text = getYmd()
                    }
                }
            }
        })
        mRvDay.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mDateType == StarDateType.YMD_HMS_TAB) {
                    mTabLayout.getTabAt(0)?.text = getYmd()
                }
            }
        })
        mRvHour.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mDateType == StarDateType.YMD_HMS_TAB) {
                    mTabLayout.getTabAt(1)?.text = getHms()
                }
            }
        })
        mRvMinute.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mDateType == StarDateType.YMD_HMS_TAB) {
                    mTabLayout.getTabAt(1)?.text = getHms()
                }
            }
        })
        mRvSecond.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mDateType == StarDateType.YMD_HMS_TAB) {
                    mTabLayout.getTabAt(1)?.text = getHms()
                }
            }
        })
        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    mRvYear.visibility = View.VISIBLE
                    mRvMonth.visibility = View.VISIBLE
                    mRvDay.visibility = View.VISIBLE
                    mRvHour.visibility = View.GONE
                    mRvMinute.visibility = View.GONE
                    mRvSecond.visibility = View.GONE
                    mTabLayout.getTabAt(0)?.text = getYmd()
                } else {
                    mRvYear.visibility = View.GONE
                    mRvMonth.visibility = View.GONE
                    mRvDay.visibility = View.GONE
                    mRvHour.visibility = View.VISIBLE
                    mRvMinute.visibility = View.VISIBLE
                    mRvSecond.visibility = View.VISIBLE
                    mTabLayout.getTabAt(1)?.text = getHms()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun getYmd(): String {
        return mYearList[yearLayoutManager!!.findFirstVisibleItemPosition() + 2] + "年" +
                mMonthList[monthLayoutManager!!.findFirstVisibleItemPosition().plus(2)] + "月" +
                mDayList[dayLayoutManager!!.findFirstVisibleItemPosition().plus(2)] + "日"
    }

    private fun getHms(): String {
        val hms: String =
            mHourList[hourLayoutManager!!.findFirstVisibleItemPosition() + 2] + "时" +
                    mMinList[minuteLayoutManager!!.findFirstVisibleItemPosition() + 2] + "分" +
                    mSecondList[secondLayoutManager!!.findFirstVisibleItemPosition() + 2] + "秒"
        return if (TextUtils.isEmpty(mHourList[hourLayoutManager!!.findFirstVisibleItemPosition() + 2])) {//第一次进来的时候
            val df = DecimalFormat("00")
            df.format(mCalendar[Calendar.HOUR_OF_DAY]) + "时" +
                    df.format(mCalendar[Calendar.MINUTE]) + "分" +
                    df.format(mCalendar[Calendar.SECOND]) + "秒"
        } else {
            hms
        }
    }

    private fun initClickListener() {
        mBtnCancel.setOnClickListener {
            mStarDatePickerListener?.cancel()
            dismiss()
        }
        mBtnConfirm.setOnClickListener {
            when (mDateType) {
                //年月日时分秒
                StarDateType.YMD_HMS -> {
                    val yearStr =
                        mYearList[yearLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val monthStr =
                        mMonthList[monthLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val dayStr =
                        mDayList[dayLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val hourStr =
                        mHourList[hourLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val minuteStr =
                        mMinList[minuteLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val secondStr =
                        mSecondList[secondLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]

                    val dateStr = "$yearStr-$monthStr-$dayStr $hourStr:$minuteStr:$secondStr"
                    val dateFormat = SimpleDateFormat(StarDateType.YMD, Locale.getDefault())
                    val date: Date?
                    date = try {
                        dateFormat.parse(dateStr)
                    } catch (e: Exception) {
                        Date()
                    }
                    mStarDatePickerListener?.confirm(dateStr, date!!)
                }

                //年月日时分秒
                StarDateType.YMD_HMS_TAB -> {
                    val df = DecimalFormat("00")
                    val yearStr =
                        mYearList[yearLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val monthStr =
                        mMonthList[monthLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val dayStr =
                        mDayList[dayLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    var hourStr: String
                    hourStr =
                        mHourList[hourLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    if (TextUtils.isEmpty(hourStr)) {
                        hourStr = df.format(mCalendar[Calendar.HOUR_OF_DAY])
                    }
                    var minuteStr: String
                    minuteStr =
                        mMinList[minuteLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    if (TextUtils.isEmpty(minuteStr)) {
                        minuteStr = df.format(mCalendar[Calendar.MINUTE])
                    }
                    var secondStr: String
                    secondStr =
                        mSecondList[secondLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    if (TextUtils.isEmpty(secondStr)) {
                        secondStr = df.format(mCalendar[Calendar.SECOND])
                    }
                    val dateStr = "$yearStr-$monthStr-$dayStr $hourStr:$minuteStr:$secondStr"
                    val dateFormat = SimpleDateFormat(StarDateType.YMD, Locale.getDefault())
                    val date: Date?
                    date = try {
                        dateFormat.parse(dateStr)
                    } catch (e: Exception) {
                        Date()
                    }
                    mStarDatePickerListener?.confirm(dateStr, date!!)
                }
                //年月日
                StarDateType.YMD -> {
                    val yearStr =
                        mYearList[yearLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val monthStr =
                        mMonthList[monthLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val dayStr =
                        mDayList[dayLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val dateStr = "$yearStr-$monthStr-$dayStr"
                    val dateFormat = SimpleDateFormat(StarDateType.YMD_HMS, Locale.getDefault())
                    val date: Date?
                    date = try {
                        dateFormat.parse(dateStr)
                    } catch (e: Exception) {
                        Date()
                    }
                    mStarDatePickerListener?.confirm("$yearStr-$monthStr-$dayStr", date!!)
                }
                //年月
                StarDateType.YM -> {
                    val yearStr =
                        mYearList[yearLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val monthStr =
                        mMonthList[monthLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val dateStr = "$yearStr-$monthStr"
                    val dateFormat = SimpleDateFormat(StarDateType.YM, Locale.getDefault())
                    val date: Date?
                    date = try {
                        dateFormat.parse(dateStr)
                    } catch (e: Exception) {
                        Date()
                    }
                    mStarDatePickerListener?.confirm(dateStr, date!!)
                }
                //时分秒
                StarDateType.HMS -> {
                    val hourStr =
                        mHourList[hourLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val minuteStr =
                        mMinList[minuteLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val secondStr =
                        mSecondList[secondLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val dateStr = "$hourStr:$minuteStr:$secondStr"
                    val dateTrueStr =
                        mCalendar[Calendar.YEAR].toString() + "-" + (mCalendar[Calendar.MONTH] - 1) + "-" +
                                mCalendar[Calendar.DAY_OF_MONTH] + " " + dateStr
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
                    val date: Date?
                    date = try {
                        dateFormat.parse(dateTrueStr)
                    } catch (e: Exception) {
                        Date()
                    }
                    mStarDatePickerListener?.confirm(dateStr, date!!)
                }
                //时分
                StarDateType.HM -> {
                    val hourStr =
                        mHourList[hourLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val minuteStr =
                        mMinList[minuteLayoutManager?.findFirstCompletelyVisibleItemPosition()!! + 2]
                    val dateStr = "$hourStr:$minuteStr"
                    val dateTrueStr =
                        mCalendar[Calendar.YEAR].toString() + "-" + (mCalendar[Calendar.MONTH] - 1) + "-" +
                                mCalendar[Calendar.DAY_OF_MONTH] + " " + dateStr
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault())
                    val date: Date?
                    date = try {
                        dateFormat.parse(dateTrueStr)
                    } catch (e: Exception) {
                        Date()
                    }
                    mStarDatePickerListener?.confirm(dateStr, date!!)
                }
                else -> {
                    dismiss()
                }
            }

            dismiss()
        }
    }

    override fun setTitle(title: String): StarDatePicker {
        return this
    }

    override fun setDateType(@StarDateType dateType: String): StarDatePicker {
        this.mDateType = dateType
        return this
    }

    override fun containUnit(containUnit: Boolean): StarDatePicker {
        this.mContainUnit = containUnit
        return this
    }

    override fun setSettingOption(option: DatePickerSettingOption): StarDatePicker {
        return this
    }

    override fun setOnSelectListener(listener: StarDatePickerListener): StarDatePicker {
        this.mStarDatePickerListener = listener
        return this
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        mDefaultDate = Date()
    }
}