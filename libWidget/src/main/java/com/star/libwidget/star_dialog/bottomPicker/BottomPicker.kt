package com.star.libwidget.star_dialog.bottomPicker

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.star.libwidget.R

/**
 *  @des: columns:列数支持1，2,3
 *  @author: sam
 *  @date: 2021/2/7
 */
class BottomPicker () : BottomSheetDialogFragment(), IStarBottomPicker {
    private lateinit var tvCancelBtn: TextView
    private lateinit var tvConfirmBtn: TextView
    private lateinit var rvFirst: RecyclerView
    private lateinit var rvSecond: RecyclerView
    private lateinit var rvThird: RecyclerView

    private var mList1: List<String>? = null
    private var mList2: List<String>? = null
    private var mList3: List<String>? = null
    private var mAnimStyleId: Int? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(context!!)
        dialog.setContentView(R.layout.widget_layout_picker_bottom)
        findView(dialog)
        initDialog(dialog)
        initClickListener()
        return dialog
    }

    private fun findView(dialog: BottomSheetDialog) {
        tvCancelBtn = dialog.findViewById(R.id.widget_tv_picker_confirm)!!
        tvConfirmBtn = dialog.findViewById(R.id.widget_tv_dialog_cancel)!!
        rvFirst = dialog.findViewById(R.id.widget_rv_first)!!
        rvSecond = dialog.findViewById(R.id.widget_rv_second)!!
        rvThird = dialog.findViewById(R.id.widget_rv_third)!!
        if (mList2 == null) {
            rvSecond.visibility = View.GONE
        } else {
            rvThird.visibility = View.VISIBLE
        }
        if (mList3 == null) {
            rvThird.visibility = View.GONE
        } else {
            rvThird.visibility = View.VISIBLE
        }

    }

    private fun initDialog(dialog: BottomSheetDialog) {
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

    private fun initClickListener() {

    }

    override fun <T> setData(list: List<T>): BottomPicker {
      return this
    }


    /**
     * 设置标题
     */
    override fun setTitle(title: String): BottomPicker {
        return this
    }

    /**
     * 设置非必要参数
     */
    override fun setSettingOption(option: BottomPickerSettingOption): BottomPicker {
        return this
    }

    /**
     *  设置监听
     */
    override fun setOnSelectListener(listener: StarBottomPickerListener): BottomPicker {
        return this
    }
}