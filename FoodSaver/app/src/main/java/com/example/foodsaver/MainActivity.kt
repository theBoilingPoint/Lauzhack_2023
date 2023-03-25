package com.example.foodsaver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var foodModels: ArrayList<FoodModel> = ArrayList<FoodModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFoodModels()


        val recyclerview = findViewById<RecyclerView>(R.id.dRecyclerView)
        val adapter = Food_RecyclerViewAdapter(foodModels)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    private fun setupFoodModels(){
        val foodModelNames = listOf<String>("stuffed sauce", "lasagna", "tea", "mysetry menu")
        val foodModelDescriptions = listOf<String>("sus menu", "the classic", "for the brits among us", "at your own risk")
        for(i in foodModelNames.indices){
            foodModels.add(FoodModel(foodModelNames.get(i), foodModelDescriptions.get(i), 0))
        }
    }
}