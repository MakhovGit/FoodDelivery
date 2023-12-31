package com.example.fooddelivery.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fooddelivery.R
import com.example.fooddelivery.model.WordData

class MenuFragmentFoodAdapter :
    RecyclerView.Adapter<MenuFragmentFoodAdapter.RecyclerItemViewHolder>() {

    private var data: List<WordData> = mutableListOf()

    fun setData(data: List<WordData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.translation_item, parent, false)
                    as View
        )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image: AppCompatImageView = itemView.findViewById(R.id.translation_item_image)
        private val title: AppCompatTextView = itemView.findViewById(R.id.translation_item_title)
        private val description: AppCompatTextView =
            itemView.findViewById(R.id.translation_item_description)

        fun bind(data: WordData) {
            title.text = data.word
            description.text = data.translation
            image.load(data.imageUrl.substring(data.imageUrl.indexOf(HTTP_PREFIX))) {
                placeholder(R.drawable.ic_launcher_background)
            }
        }
    }

    companion object {
        private const val HTTP_PREFIX = "http"
    }
}