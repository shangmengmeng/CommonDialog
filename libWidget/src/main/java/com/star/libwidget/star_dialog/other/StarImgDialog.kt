package com.star.libwidget.star_dialog.other

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.star.libwidget.R

/**
 * @des 图片Dialog
 * @date 2021/5/12
 * @author sam
 */
class StarImgDialog(private var fragmentActivity: FragmentActivity) : DialogFragment() {
    private var createImageEngine: CreateImageEngine? = null
    private var imgDialogListener:ImgDialogListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context!!)
        dialog.setContentView(R.layout.widget_layout_imag_dialog)
        initDialog(dialog)
        return dialog
    }

    private fun initDialog(dialog: Dialog) {
        val w = dialog.window
        w!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        w.setWindowAnimations(R.style.StarDialog)
        val imageView = dialog.findViewById<ImageView>(R.id.widget_iv_dialog_img)
        val cancelBtn = dialog.findViewById<ImageView>(R.id.widget_iv_dialog_cancel)
        createImageEngine?.covert(imageView)
        imageView.setOnClickListener {
            imgDialogListener?.onImgClick()
        }
        cancelBtn.setOnClickListener {
            dismiss()
            imgDialogListener?.onCancel()
        }

    }

    fun setImage(createImageEngine: CreateImageEngine):StarImgDialog {
        this.createImageEngine = createImageEngine
        return this
    }
    fun setOnClickListener(imgDialogListener: ImgDialogListener):StarImgDialog{
        this.imgDialogListener = imgDialogListener
        return this
    }

    interface ImgDialogListener {
        fun onImgClick()
        fun onCancel()
    }

    interface CreateImageEngine {
        fun covert(imageView: ImageView)
    }
}