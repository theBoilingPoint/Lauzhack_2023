package com.example.theapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimeSlotAdapter(private val context: Context, private val timeSlots: List<LocalTime>, private val onTimeSlotSelected: (LocalTime) -> Unit) : RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {

    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.time_slot_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val timeSlot = timeSlots[position]

        holder.timeSlotText.text = "${timeSlot.format(DateTimeFormatter.ofPattern("h:mm a"))} - ${timeSlot.plusMinutes(15).format(DateTimeFormatter.ofPattern("h:mm a"))}"
        holder.itemView.setBackgroundColor(if (position == selectedItem) Color.LTGRAY else Color.WHITE)

        holder.itemView.setOnClickListener {
            onTimeSlotSelected(timeSlot)
            selectedItem = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = timeSlots.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeSlotText: TextView = itemView.findViewById(R.id.time_slot_text)
    }
}