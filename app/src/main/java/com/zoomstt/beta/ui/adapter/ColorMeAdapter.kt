package com.zoomstt.beta.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.zoomstt.beta.R
import com.zoomstt.beta.databinding.ItemColorSelectBinding
import com.zoomstt.beta.ui.base.BaseRecyclerViewAdapter
import com.zoomstt.beta.zoombeta.ColorResource
import com.zoomstt.beta.zoombeta.ColorType

class ColorMeAdapter(val context: Context,val onClickItem: (color: Int) -> Unit) :
    BaseRecyclerViewAdapter<Int, ItemColorSelectBinding>(object :
        DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem == newItem
        }
    }) {

    private var colorMe = ColorResource.list()

    private var selectedPosition = -1

    override val layoutRes = R.layout.item_color_select

    override fun bindData(itemBinding: ItemColorSelectBinding, position: Int) {
        super.bindData(itemBinding, position)
        val content = colorMe[position]
        itemBinding.apply {
            ivSelectedColor.isSelected = selectedPosition == position

            bindViews(content.color(context))
            tvColor.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
                onClickItem.invoke(content.color(context))
            }
        }
    }

    private fun ItemColorSelectBinding.bindViews(content: Int) {
        tvColor.setBackgroundColor(content)
    }
}