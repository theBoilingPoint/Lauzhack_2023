package com.example.theapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TimeSlotActivity : AppCompatActivity() {

    private lateinit var foodItem: String
    private lateinit var timeSlot: String
    private lateinit var time_slot_list_view: ListView
    private lateinit var order_button: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_slot)

        // Get the food item and price from the intent
        foodItem = intent.getStringExtra("food_item") ?: "a"
        val foodPrice = intent.getStringExtra("food_price")

        // Set the food item and price in the layout
        val foodText: TextView = findViewById(R.id.food_item_text)
        foodText.text = "$foodItem - $foodPrice"

        // Set up the list of time slots
        val timeSlots = createTimeSlots()

        // Create an adapter to display the time slots and meal price in a ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, timeSlots.map { "$it" })

        time_slot_list_view = findViewById(R.id.time_slot_list_view)
        order_button = findViewById(R.id.order_button)

        // Set the adapter on the ListView
        time_slot_list_view.adapter = adapter
        time_slot_list_view.choiceMode = ListView.CHOICE_MODE_SINGLE

        // Set up a click listener on the "Order" button
        order_button.setOnClickListener {
            // Get the selected time slot
            val position = time_slot_list_view.checkedItemPosition
            if (position != ListView.INVALID_POSITION) {
                timeSlot = timeSlots[position]

                // Launch the confirmation activity and pass the food item, price, and time slot
                val intent = Intent(this, ConfirmationActivity::class.java).apply {
                    putExtra("food_item", foodItem)
                    putExtra("food_price", foodPrice)
                    putExtra("time_slot", timeSlot)
                }
                startActivity(intent)
            } else {
                // If no time slot is selected, show a toast message
                Toast.makeText(this, "Please select a time slot", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createTimeSlots(): List<String> {
        val timeSlots = mutableListOf<String>()
        val startTime = LocalTime.of(11, 0)
        val endTime = LocalTime.of(13, 0)

        var timeSlot = startTime
        while (timeSlot.isBefore(endTime)) {
            val endTimeSlot = timeSlot.plusMinutes(15)
            timeSlots.add("${timeSlot.format(DateTimeFormatter.ofPattern("h:mm a"))} - ${endTimeSlot.format(DateTimeFormatter.ofPattern("h:mm a"))}")
            timeSlot = endTimeSlot
        }

        return timeSlots
    }
}