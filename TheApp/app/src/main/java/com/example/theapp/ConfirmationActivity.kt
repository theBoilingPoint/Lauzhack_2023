package com.example.theapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        // Get the selected food item and selected time slot from the intent
        val foodItem = intent.getStringExtra("food_item")
        val timeSlot = intent.getStringExtra("time_slot")
        val foodPrice = intent.getStringExtra("food_price")

        // Display the selected food item and selected time slot on the confirmation screen
        val foodItemTextView = findViewById<TextView>(R.id.food_item_text_view)
        val timeSlotTextView = findViewById<TextView>(R.id.time_slot_text_view)
        foodItemTextView.text = "$foodItem - $foodPrice"
        timeSlotTextView.text = timeSlot

        // Set up the "pay" button
        val payButton = findViewById<Button>(R.id.pay_button)
        payButton.setOnClickListener {
            // Do nothing for now
        }
    }
}