package com.star.commondialog

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.star.libwidget.star_dialog.bottomPicker.BottomPicker
import com.star.libwidget.star_dialog.common.DialogSettingOption
import com.star.libwidget.star_dialog.common.StarDialog
import com.star.libwidget.star_dialog.common.StarDialogClickListener
import com.star.libwidget.star_dialog.date.StarDatePicker
import com.star.libwidget.star_dialog.date.StarDatePickerListener
import com.star.libwidget.star_dialog.date.StarDateType
import com.star.libwidget.star_dialog.input.InputDialogSettingOption
import com.star.libwidget.star_dialog.input.StarInputDialog
import com.star.libwidget.star_dialog.input.StarInputDialogClickListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *  主页测试
 *  @author sam
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_dialog_normal.setOnClickListener(this)
        btn_dialog_title.setOnClickListener(this)
        btn_dialog_anim.setOnClickListener(this)
        btn_dialog_single.setOnClickListener(this)
        btn_dialog_custom.setOnClickListener(this)
        btn_dialog_title.setOnClickListener(this)
        btn_dialog_title.setOnClickListener(this)
        btn_dialog_input.setOnClickListener(this)
        btn_dialog_dateTime.setOnClickListener(this)
        btn_dialog_date.setOnClickListener(this)
        btn_dialog_date_ym.setOnClickListener(this)
        btn_dialog_time_hms.setOnClickListener(this)
        btn_dialog_time_hm.setOnClickListener(this)
        btn_dialog_dateTime_tab.setOnClickListener(this)
        btn_dialog_bottomPicker.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //普通
            R.id.btn_dialog_normal -> {
                StarDialog(this)
                        .setContent("请注意这张是假币")
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
            //带标题
            R.id.btn_dialog_title -> {
                StarDialog(this)
                        .setTitle("我带标题")
                        .setContent("请注意这张是假币")
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
            //单按钮
            R.id.btn_dialog_single -> {
                StarDialog(this)
                        .single(true)
                        .setTitle("我带标题")
                        .setContent("请注意这张是假币")
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
            //动画
            R.id.btn_dialog_anim -> {
                StarDialog(this)
                        .setTitle("我带标题")
                        .setContent("请注意这张是假币")
                        .setAnimStyle(R.style.StarDialogStyle2)
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
            //自定义
            R.id.btn_dialog_custom -> {
                val option = DialogSettingOption()
                option.titleColor = Color.parseColor("#333333")
                option.contentColor = Color.parseColor("#00b7ee")
                option.dividerColor = Color.parseColor("#00b7ee")
                option.cancelTextColor = Color.parseColor("#00b7ee")
                option.confirmTextColor = Color.parseColor("#00b7ee")
                StarDialog(this)
//                    .setTitle("我是标题")
                        .setContent("请注意这张是假币")
                        .setOptionSetting(option)
                        .setOnStarDialogClickListener(object : StarDialogClickListener {
                            override fun onConfirm() =
                                    Toast.makeText(applicationContext, "我确定了", Toast.LENGTH_SHORT).show()

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .show()
            }
            //输入框
            R.id.btn_dialog_input -> {
                //
                val option = InputDialogSettingOption()
                option.titleColor = Color.parseColor("#666666")
                option.contentColor = Color.parseColor("#00b7ee")
                option.dividerColor = Color.parseColor("#00b7ee")
                option.cancelTextColor = Color.parseColor("#00b7ee")
                option.confirmTextColor = Color.parseColor("#00b7ee")
                StarInputDialog(this)
                        .setTitle("我是标题")
                        .singleButton(true)
                        .setOptionSetting(option)
                        .setOnStarInputDialogClickListener(object : StarInputDialogClickListener {
                            override fun onConfirm(data: String) {
                                Toast.makeText(applicationContext, "我确定了$data", Toast.LENGTH_SHORT)
                                        .show()
                            }

                            override fun onCancel() {
                                Toast.makeText(applicationContext, "我取消了", Toast.LENGTH_SHORT).show()
                            }

                        })
                        .show()
            }
            //日期 年月日时分秒
            R.id.btn_dialog_dateTime -> {
                StarDatePicker()
                        .setDateType(StarDateType.YMD_HMS)
                        .setOnSelectListener(object : StarDatePickerListener {
                            override fun confirm(dateStr: String, date: Date) {
                                Toast.makeText(applicationContext, dateStr, Toast.LENGTH_SHORT).show()
                            }

                            override fun cancel() {

                            }

                        })
                        .show(supportFragmentManager, "")
            }

            //日期 年月日时分秒
            R.id.btn_dialog_dateTime_tab -> {
                StarDatePicker()
                        .setDateType(StarDateType.YMD_HMS_TAB)
                        .setOnSelectListener(object : StarDatePickerListener {
                            override fun confirm(dateStr: String, date: Date) {
                                Toast.makeText(applicationContext, dateStr, Toast.LENGTH_SHORT).show()
                            }

                            override fun cancel() {

                            }

                        })
                        .show(supportFragmentManager, "")
            }
            //日期 年月日
            R.id.btn_dialog_date -> {
                StarDatePicker()
                        .setDateType(StarDateType.YMD)
                        .setOnSelectListener(object : StarDatePickerListener {
                            override fun confirm(dateStr: String, date: Date) {
                                Toast.makeText(applicationContext, dateStr, Toast.LENGTH_SHORT).show()
                            }

                            override fun cancel() {

                            }

                        })
                        .show(supportFragmentManager, "")
            }
            //日期 年月日
            R.id.btn_dialog_date_ym -> {
                StarDatePicker()
                        .setDateType(StarDateType.YM)
                        .setOnSelectListener(object : StarDatePickerListener {
                            override fun confirm(dateStr: String, date: Date) {
                                Toast.makeText(applicationContext, dateStr, Toast.LENGTH_SHORT).show()
                            }

                            override fun cancel() {

                            }

                        })
                        .show(supportFragmentManager, "")
            }
            //时分秒
            R.id.btn_dialog_time_hms -> {
                StarDatePicker()
                        .setDateType(StarDateType.HMS)
                        .setOnSelectListener(object : StarDatePickerListener {
                            override fun confirm(dateStr: String, date: Date) {
                                Toast.makeText(applicationContext, dateStr, Toast.LENGTH_SHORT).show()
                            }

                            override fun cancel() {

                            }

                        })
                        .show(supportFragmentManager, "")
            }
            //时分
            R.id.btn_dialog_time_hm -> {
                StarDatePicker()
                        .setDateType(StarDateType.HM)
                        .setOnSelectListener(object : StarDatePickerListener {
                            override fun confirm(dateStr: String, date: Date) {
                                Toast.makeText(applicationContext, dateStr, Toast.LENGTH_SHORT).show()
                            }

                            override fun cancel() {

                            }

                        })
                        .show(supportFragmentManager, "")
            }
            R.id.btn_dialog_bottomPicker -> {
                //底部弹框
                /* val mBottomSheetDialog =  BottomSheetDialog(this);
                      val view1 = layoutInflater.inflate(R.layout.widget_layout_input_dialog, null);
                  mBottomSheetDialog.setContentView(view1);
                      mBottomSheetDialog.window!!.findViewById<View>(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
                  mBottomSheetDialog.show();*/
                val mDialog = BottomPicker()
                mDialog.setData(ArrayList<String>())
            }


        }
    }
}