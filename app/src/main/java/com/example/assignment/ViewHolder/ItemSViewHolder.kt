package com.example.assignment.ViewHolder

import android.util.Log.d
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import com.example.assignment.Interface.ItemSClickListener
import com.example.assignment.R

class ItemSViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    lateinit var itemName_text: TextView
    lateinit var color_text: TextView

    lateinit var itemS_ClickListener: ItemSClickListener


    fun setItemSClickListener(itemS_ClickListener: ItemSClickListener) {
        this.itemS_ClickListener = itemS_ClickListener
    }

    init {
        itemName_text = itemView.findViewById(R.id.itemName) as TextView
        color_text = itemView.findViewById(R.id.colorFamily) as TextView


        itemView.setOnClickListener {
            view ->  itemS_ClickListener.onClick(view, adapterPosition)
        }
    }


}

