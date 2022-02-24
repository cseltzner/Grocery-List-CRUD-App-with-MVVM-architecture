package com.cseltz.android.grocerylist.ui.list.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.data.Item

class ListFragmentAdapter(private val listener: OnRecyclerViewItemClick): ListAdapter<Item, ListFragmentAdapter.ListFragmentViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_list_item, parent, false)

        return ListFragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListFragmentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ListFragmentViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val listItem = itemView.findViewById<ConstraintLayout>(R.id.list_item_entire_item)
        val title = itemView.findViewById<TextView>(R.id.list_item_title)
        val quantity = itemView.findViewById<TextView>(R.id.list_item_amount)
        val done = itemView.findViewById<CheckBox>(R.id.list_item_is_complete)

        init {
            listItem.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener.onLongPress(item)
                    true
                } else false
            }

            done.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener.onCheckedChanged(item, isChecked)
                }
            }

            listItem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener.onClick(item)
                }
            }
        }

        fun bind(item: Item) {
            title.text = item.name
            quantity.text = item.quantity.toString()
            done.isChecked = item.found
            title.paint.isStrikeThruText = done.isChecked
        }
    }

    interface OnRecyclerViewItemClick {
        fun onClick(item: Item)
        fun onLongPress(item: Item)
        fun onCheckedChanged(item: Item, isChecked: Boolean)
    }

}

class DiffUtilCallback: DiffUtil.ItemCallback<Item>() {
    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }
}