package com.example.foodsaver

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Food_RecyclerViewAdapter(private val mList: List<FoodModel>) : RecyclerView.Adapter<Food_RecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

//        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.nameText.text = ItemsViewModel.foodName
        holder.descText.text = ItemsViewModel.foodDescription
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class MyViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        init {
            // Set a click listener on the item view
            itemView.setOnClickListener {
                // Handle the click event for this item
                // For example, you can launch a new activity or show a dialog
            }
        }
        val nameText: TextView = itemView.findViewById(R.id.plate_name_text)
        val descText: TextView = itemView.findViewById(R.id.description_text)
    }

}