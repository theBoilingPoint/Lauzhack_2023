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

    val foodModelNames: MutableMap<LocalDate, MutableList<String>> = mutableMapOf()
    val foodModelDescriptions: MutableMap<LocalDate, MutableList<String>> = mutableMapOf()
    private var foodModels: ArrayList<FoodModel> = ArrayList<FoodModel>()
    private lateinit var adapter: Food_RecyclerViewAdapter
    private var date: LocalDate = LocalDate.now()
    private lateinit var dateText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hardCodeData()
        updateFoodModel()



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
        updateFoodModel()

        adapter.notifyDataSetChanged()
    }

    private fun updateFoodModel(){
        val names = foodModelNames[date]
        val descriptions = foodModelDescriptions[date]
        if(names == null || descriptions == null){
            foodModels = ArrayList<FoodModel>()
        }else{
            foodModels.clear()
            for(i in names.indices){
                foodModels.add(FoodModel(names[i], descriptions[i], 0))
            }
        }


    }
    private fun hardCodeData(){


        // Hardcode some elements in the map
        val date1 = LocalDate.now()
        foodModelNames.put(date1, mutableListOf("vegan item 1", "vegan item 2"))
        foodModelDescriptions.put(date1, mutableListOf("sad day", "go eat migros"))

        val date2 = date1.plusDays(1)
        foodModelNames.put(date2, mutableListOf("stuffed sauce", "pasta with pasta"))
        foodModelDescriptions.put(date2, mutableListOf("beware", "yum"))

        val date3 = date2.plusDays(1)
        foodModelNames.put(date3, mutableListOf("water", "tea"))
        foodModelDescriptions.put(date3, mutableListOf("hydrate", "hydrate but less"))

    }
}