package com.example.foodsaver

import android.media.Image
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private var foodModels: ArrayList<FoodModel> = ArrayList<FoodModel>()
    private lateinit var adapter: Food_RecyclerViewAdapter
    private var date: LocalDate = LocalDate.now()
    private lateinit var dateText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFoodModels()


        val recyclerview = findViewById<RecyclerView>(R.id.dRecyclerView)
        adapter = Food_RecyclerViewAdapter(foodModels)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        dateText = findViewById<TextView>(R.id.date_text)
        dateText.text = date.toString()

        val nextButton = findViewById<ImageButton>(R.id.nextBtn)
        val prevButton = findViewById<ImageButton>(R.id.previousBtn)
        nextButton.setOnClickListener{changeDate(1)}
        prevButton.setOnClickListener{changeDate(-1)}
    }

    private fun changeDate(delta:Long){
        date = date.plusDays(delta)
        dateText.text = date.toString()

        
        adapter.notifyDataSetChanged()
    }
    private fun setupFoodModels(){
        val foodModelNames = listOf<String>("stuffed sauce", "lasagna", "tea", "mysetry menu")
        val foodModelDescriptions = listOf<String>("sus menu", "the classic", "for the brits among us", "at your own risk")
        for(i in foodModelNames.indices){
            foodModels.add(FoodModel(foodModelNames.get(i), foodModelDescriptions.get(i), 0))
        }
    }
}