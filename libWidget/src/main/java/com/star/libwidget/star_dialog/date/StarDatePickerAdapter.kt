package com.star.libwidget.star_dialog.date

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.star.libwidget.R

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/3
 */
class StarDatePickerAdapter(
    private var mContext: Context,
    private var mList: List<String>,
    private var mContainUnit: Boolean,
    private var mUnitType: String,
    private var mTextColor: Int?,
    private var mTextSize: Float?
) :
    RecyclerView.Adapter<StarDatePickerAdapter.StarDatePickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarDatePickerViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.widget_item_picker_date, parent, false)
        return StarDatePickerViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StarDatePickerViewHolder, position: Int) {
        val tvValue = holder.itemView.findViewById<TextView>(R.id.widget_tv_picker_value)
        if (mContainUnit) {
            if (position > 1 && position < mList.size - 2) {
                tvValue.text = mList[position] + mUnitType
            }else{
                tvValue.text = " "
            }
        } else {
            tvValue.text = mList[position]
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class StarDatePickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}