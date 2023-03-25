package com.example.theapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mealListView: ListView
    private lateinit var goToCartButton: Button
    private var selectedMealPosition: Int? = null

    private val mealList = listOf(
        Meal("Pizza", "10.00 CHF", R.drawable.pizza),
        Meal("Piccata de poulet", "12.00 CHF", R.drawable.piccata_de_poulet),
        Meal("Focaccia mozzarella jambon cru", "7.10 CHF", R.drawable.focaccia_mozzarella_jambon_cru),
        Meal("Tortellini stuffed with cheese", "8.60 CHF", R.drawable.tortellini_stuffed_with_cheese)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mealListView = findViewById(R.id.meal_list_view)
        goToCartButton = findViewById(R.id.go_to_cart_button)
        goToCartButton.isEnabled = false

        val adapter = MealListAdapter(this, mealList)
        mealListView.adapter = adapter

        mealListView.setOnItemClickListener { _, view, position, _ ->
            // Remove highlight from the previously selected item
            selectedMealPosition?.let {
                mealListView.getChildAt(it)?.setBackgroundColor(Color.TRANSPARENT)
            }

            // Get the selected meal and highlight the list item
            selectedMealPosition = position
            view.setBackgroundColor(Color.LTGRAY)

            // Enable the "go to cart" button
            goToCartButton.isEnabled = true
        }

        goToCartButton.setOnClickListener {
            val selectedMeal = mealList[selectedMealPosition ?: 0]
            val intent = Intent(this, TimeSlotActivity::class.java).apply {
                putExtra("food_item", selectedMeal.name)
                putExtra("food_price", selectedMeal.price)
            }
            startActivity(intent)
        }
    }

    private class MealListAdapter(context: Context, private val mealList: List<Meal>) :
        ArrayAdapter<Meal>(context, 0, mealList) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.list_item_meal, parent, false)
            }
            val meal = mealList[position]
            view?.findViewById<TextView>(R.id.meal_name)?.text = meal.name
            view?.findViewById<TextView>(R.id.meal_price)?.text = meal.price
            view?.findViewById<ImageView>(R.id.meal_image_view)?.setImageResource(meal.resource)

            // Set the background color of the view based on whether it is selected or not
            val isSelected = position == (parent as ListView).selectedItemPosition
            view?.setBackgroundColor(if (isSelected) Color.LTGRAY else Color.TRANSPARENT)

            return view!!
        }
    }

    data class Meal(val name: String, val price: String, val resource: Int)
}