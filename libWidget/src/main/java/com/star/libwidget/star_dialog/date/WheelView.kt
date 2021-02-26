package com.star.libwidget.star_dialog.date

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 *  @des:
 *  @author: sam
 *  @date: 2021/2/3
 */
class WheelView : RecyclerView {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        return super.fling(velocityX, (velocityY * 1.5).toInt())
    }
}