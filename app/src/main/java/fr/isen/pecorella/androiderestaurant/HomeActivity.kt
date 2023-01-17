package fr.isen.pecorella.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.pecorella.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this@HomeActivity, ActivityCategorie::class.java)

        binding.buttonStarter.setOnClickListener(){
            //Toast.makeText(this,"Entrees button clicked", Toast.LENGTH_SHORT).show()
            intent.putExtra("categoryName", "Entrees")
            val mealList = resources.getStringArray(R.array.dish_entrees).toList() as ArrayList<String>
            intent.putExtra("List_Meal", mealList)
            startActivity(intent);
        }

        binding.buttonMainCourse.setOnClickListener(){
            //Toast.makeText(this,"Plats button clicked", Toast.LENGTH_SHORT).show()
            intent.putExtra("categoryName", "Entrees")
            val mealList = resources.getStringArray(R.array.dish_plats).toList() as ArrayList<String>
            intent.putExtra("List_Meal", mealList)
            startActivity(intent);
        }

        binding.buttonDessert.setOnClickListener(){
            //Toast.makeText(this,"Desserts button clicked", Toast.LENGTH_SHORT).show()
            intent.putExtra("categoryName", "Entrees")
            val mealList = resources.getStringArray(R.array.dish_desserts).toList() as ArrayList<String>
            intent.putExtra("List_Meal", mealList)
            startActivity(intent);
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "L'activité Home a été détruite")
    }
}