package com.example.fooddelivery.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelivery.R

class MenuFragmentBannersAdapter :
    RecyclerView.Adapter<MenuFragmentBannersAdapter.RecyclerItemViewHolder>() {
    private var listBanners: MutableList<Int> = mutableListOf()

    fun setData(data: List<Int>) {
        listBanners = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.banners_item, parent, false)
                    as View
        )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(listBanners[position])
    }

    override fun getItemCount() = listBanners.size

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image: AppCompatImageView = itemView.findViewById(R.id.banners_item_image)

        fun bind(data: Int) {
            image.setImageResource(data)
        }
    }
}