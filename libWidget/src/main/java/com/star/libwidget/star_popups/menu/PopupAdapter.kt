package com.star.libwidget.star_popups.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.star.libwidget.R

/**
 *  @des: 列表 Adapter
 *  @author: sam
 *  @date: 2021/1/19
 */
class PopupAdapter(var mDataList: List<StarPopupMenu.ItemBean>, var mContext: Context) :
    RecyclerView.Adapter<PopupAdapter.PopupAdapterViewHolder>() {

    private lateinit var myViewHolder: PopupAdapterViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopupAdapterViewHolder {
        val itemView =
            LayoutInflater.from(mContext).inflate(R.layout.widget_item_popup, parent, false)
        myViewHolder = PopupAdapterViewHolder(itemView)
        return myViewHolder
    }

    override fun onBindViewHolder(holder: PopupAdapterViewHolder, position: Int) {
        holder.setData(mDataList[position])
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClickListener(position, mDataList[position].name)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    //默认为静态内部类
    class PopupAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvItem = itemView.findViewById<TextView>(R.id.tv_item_pop)
        private var ivItem = itemView.findViewById<ImageView>(R.id.iv_item_pop)

        fun setData(data: StarPopupMenu.ItemBean) {
            tvItem.text = data.name
            ivItem.setImageResource(data.iconId)
        }
    }

    interface ItemClickListener {
        fun onItemClickListener(position: Int, name: String)
    }

    private var itemClickListener: ItemClickListener? = null
    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}