package com.star.libwidget.star_dialog.input

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.star.libwidget.R


/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/2
 */
class StarInputDialog(private var fragmentActivity: FragmentActivity) : DialogFragment(), IInputStarDialogMethod {
    private var mTitle: String? = null
    private var mConfirmText: String? = null
    private var mCancelText: String? = null
    private var mAnimStyleId: Int? = null
    private var mIsSingleButton: Boolean? = null
    private var mDialogClickListener: StarInputDialogClickListener? = null
    private var mSettingOption: InputDialogSettingOption? = null

   

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(activity!!)
        dialog.setContentView(R.layout.widget_layout_input_dialog)
        initDialog(dialog)

        return dialog
    }

    private fun initDialog(dialog: Dialog) {
        val titleTextView = dialog.findViewById<TextView>(R.id.widget_tv_dialog_title)
        val contentEditText = dialog.findViewById<EditText>(R.id.widget_et_dialog_content)
        val confirmBtn = dialog.findViewById<TextView>(R.id.widget_tv_dialog_confirm)
        val cancelBtn = dialog.findViewById<TextView>(R.id.widget_tv_dialog_cancel)
        val divider1 = dialog.findViewById<View>(R.id.widget_tv_dialog_divider_1)
        val divider2 = dialog.findViewById<View>(R.id.widget_tv_dialog_divider_2)

        if (!TextUtils.isEmpty(mTitle)) {
            titleTextView.visibility = View.VISIBLE
            titleTextView.text = mTitle
        }
        if (!TextUtils.isEmpty(mConfirmText)) {
            confirmBtn.text = mConfirmText
        }
        if (!TextUtils.isEmpty(mCancelText)) {
            cancelBtn.text = mCancelText
        }
        val w = dialog.window
        w!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (mAnimStyleId != null && mAnimStyleId != 0) {
            w.setWindowAnimations(mAnimStyleId!!)
        } else {
            w.setWindowAnimations(R.style.StarDialog)
        }
        if (mIsSingleButton == true) {
            divider2.visibility = View.GONE
            cancelBtn.visibility = View.GONE
        }
        //配置其它设置（非必要）
        if (mSettingOption != null) {
            if (mSettingOption!!.titleColor != null && mSettingOption!!.titleColor != 0) {
                titleTextView.setTextColor(mSettingOption!!.titleColor!!)
            }
            if (mSettingOption!!.titleSize != null) {
                titleTextView.textSize = mSettingOption!!.titleSize!!
            }
            if (mSettingOption!!.contentColor != null && mSettingOption!!.contentColor != 0) {
                contentEditText.setTextColor(mSettingOption!!.contentColor!!)
            }
            if (mSettingOption!!.contentSize != null) {
                contentEditText.textSize = mSettingOption!!.contentSize!!
            }
            if (mSettingOption!!.confirmTextColor != null && mSettingOption!!.confirmTextColor != 0) {
                confirmBtn.setTextColor(mSettingOption!!.confirmTextColor!!)
            }
            if (mSettingOption!!.confirmTextSize != null) {
                confirmBtn.textSize = mSettingOption!!.confirmTextSize!!
            }
            if (mSettingOption!!.cancelTextColor != null && mSettingOption!!.cancelTextColor != 0) {
                cancelBtn.setTextColor(mSettingOption!!.cancelTextColor!!)
            }
            if (mSettingOption!!.cancelTextSize != null) {
                cancelBtn.textSize = mSettingOption!!.cancelTextSize!!
            }
            if (mSettingOption!!.dividerColor != null && mSettingOption!!.dividerColor != 0) {

                divider1.setBackgroundColor(mSettingOption!!.dividerColor!!)
                divider2.setBackgroundColor(mSettingOption!!.dividerColor!!)
            }
        }
        confirmBtn.setOnClickListener {
            mDialogClickListener?.onConfirm(contentEditText.text.toString().trim())
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            mDialogClickListener?.onCancel()
            dialog.dismiss()
        }
    }
    fun show() {
        val manager = fragmentActivity.supportFragmentManager
        show(manager, "")
    }

    override fun setTitle(title: String): StarInputDialog {
        this.mTitle = title
        return this
    }

    override fun setConfirmText(confirmText: String): StarInputDialog {
        this.mConfirmText = confirmText
        return this
    }

    override fun setCancelText(cancelText: String): StarInputDialog {
        this.mCancelText = cancelText
        return this
    }

    override fun singleButton(isSingleButton: Boolean): StarInputDialog {
        this.mIsSingleButton = isSingleButton
        return this
    }

    override fun setAnimStyle(styleId: Int): StarInputDialog {
        this.mAnimStyleId = styleId
        return this
    }

    override fun setOnStarInputDialogClickListener(dialogClickListener: StarInputDialogClickListener): StarInputDialog {
        this.mDialogClickListener = dialogClickListener
        return this
    }

    override fun setOptionSetting(option: InputDialogSettingOption): StarInputDialog {
        this.mSettingOption = option
        return this
    }

   
}