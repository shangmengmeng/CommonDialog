package com.star.libwidget.star_dialog.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.star.libwidget.R

/**
 *  @des:弹框
 *  @author: sam
 *  @date: 2021/2/1
 */

class StarDialog() : DialogFragment() {
    private var mTitle: String? = null
    private lateinit var mContent: String
    private var mConfirmText: String? = null
    private var mCancelText: String? = null
    private var mAnimStyleId: Int? = null
    private var mIsSingleButton: Boolean? = null
    private var mDialogClickListener: StarDialogClickListener? = null
    private var mSettingOption: DialogSettingOption? = null

    constructor(
        title: String?,
        content: String,
        confirmText: String?,
        cancelText: String?,
        animStyleId: Int?,
        isSingleButton: Boolean?,
        dialogClickListener: StarDialogClickListener?,
        settingOption: DialogSettingOption?
    ) : this() {
        this.mTitle = title
        this.mContent = content
        this.mConfirmText = confirmText
        this.mCancelText = cancelText
        this.mAnimStyleId = animStyleId
        this.mIsSingleButton = isSingleButton
        this.mDialogClickListener = dialogClickListener
        this.mSettingOption = settingOption
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.widget_layout_common_dialog)
        initDialog(dialog)
        return dialog
    }

    private fun initDialog(dialog: Dialog) {
        val w = dialog.window
        w!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (mAnimStyleId != null && mAnimStyleId != 0) {
            w.setWindowAnimations(mAnimStyleId!!)
        } else {
            w.setWindowAnimations(R.style.StarDialog)
        }

        val titleTextView = dialog.findViewById<TextView>(R.id.widget_tv_dialog_title)
        val contentTextView = dialog.findViewById<TextView>(R.id.widget_tv_dialog_content)
        val confirmBtn = dialog.findViewById<TextView>(R.id.widget_tv_dialog_confirm)
        val cancelBtn = dialog.findViewById<TextView>(R.id.widget_tv_dialog_cancel)
        val divider1 = dialog.findViewById<View>(R.id.widget_tv_dialog_divider_1)
        val divider2 = dialog.findViewById<View>(R.id.widget_tv_dialog_divider_2)

        contentTextView.text = mContent
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
                contentTextView.setTextColor(mSettingOption!!.contentColor!!)
            }
            if (mSettingOption!!.contentSize != null) {
                contentTextView.textSize = mSettingOption!!.contentSize!!
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
            mDialogClickListener?.onConfirm()
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            mDialogClickListener?.onCancel()
            dialog.dismiss()
        }
    }

    class Builder : IStarDialogMethod {
        private var title: String? = null
        private lateinit var content: String
        private var confirmText: String? = null
        private var cancelText: String? = null
        private var animStyleId: Int? = null
        private var isSingleButton: Boolean? = null
        private var dialogClickListener: StarDialogClickListener? = null
        private var settingOption: DialogSettingOption? = null


        override fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        override fun setContent(content: String): Builder {
            this.content = content
            return this
        }

        override fun setConfirmText(confirmText: String): Builder {
            this.confirmText = confirmText
            return this
        }

        override fun setCancelText(cancelText: String): Builder {
            this.cancelText = cancelText
            return this
        }

        override fun single(isSingleButton: Boolean): Builder {
            this.isSingleButton = isSingleButton
            return this
        }

        override fun setAnimStyle(styleId: Int): Builder {
            this.animStyleId = styleId
            return this
        }

        override fun setOnStarDialogClickListener(dialogClickListener: StarDialogClickListener): Builder {
            this.dialogClickListener = dialogClickListener
            return this
        }

        override fun setOptionSetting(option: DialogSettingOption): Builder {
            this.settingOption = option
            return this
        }

        override fun show(manager: FragmentManager) {
            val starDialog =
                StarDialog(
                    title,
                    content,
                    confirmText,
                    cancelText,
                    animStyleId,
                    isSingleButton,
                    dialogClickListener,
                    settingOption
                )
            starDialog.show(manager, "")
        }
    }
}