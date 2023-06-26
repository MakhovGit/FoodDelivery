package com.example.fooddelivery.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R
import com.example.fooddelivery.utils.ZERO

class MenuFragmentMenuAdapter :
    RecyclerView.Adapter<MenuFragmentMenuAdapter.RecyclerItemViewHolder>() {
    private var menu: MutableList<String> = mutableListOf()

    fun setData(data: List<String>) {
        menu = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.menu_line_item, parent, false)
                    as View
        )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(menu[position], position)
    }

    override fun getItemCount() = menu.size

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val text: AppCompatTextView = itemView.findViewById(R.id.menu_line_item_text)

        fun bind(data: String, position: Int) {
            text.text = data
            if (position == Int.ZERO) {
                text.setTextColor(itemView.context.getColor(R.color.button_pressed_text_color))
                itemView.setBackgroundColor(itemView.context.getColor(R.color.button_pressed_background_color))
            }
        }
    }
}