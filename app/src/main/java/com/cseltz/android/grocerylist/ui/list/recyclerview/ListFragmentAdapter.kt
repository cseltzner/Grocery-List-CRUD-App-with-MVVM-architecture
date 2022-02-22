package com.cseltz.android.grocerylist.ui.list.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.data.Item

class ListFragmentAdapter: ListAdapter<Item, ListFragmentAdapter.ListFragmentViewHolder>(DiffUtilCallback()) {

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
        val title = itemView.findViewById<TextView>(R.id.list_item_title)
        val quantity = itemView.findViewById<TextView>(R.id.list_item_amount)
        val done = itemView.findViewById<CheckBox>(R.id.list_item_is_complete)

        fun bind(item: Item) {
            title.text = item.name
            quantity.text = item.quantity.toString()
            done.isChecked = item.found
        }
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