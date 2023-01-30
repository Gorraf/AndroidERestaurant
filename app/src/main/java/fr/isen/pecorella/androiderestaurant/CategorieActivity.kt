package fr.isen.pecorella.androiderestaurant


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.pecorella.androiderestaurant.databinding.ActivityCategorieBinding
import fr.isen.pecorella.androiderestaurant.modele.DataResult
import fr.isen.pecorella.androiderestaurant.modele.Items
import org.json.JSONObject


class CategorieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategorieBinding
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategorieBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        category = intent.getStringExtra("categoryName") ?: ""
        val actionBar = supportActionBar
        actionBar?.title = category

        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = DishAdapter(arrayListOf()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
        }
        loadDishesFromAPI()
    }
        private fun loadDishesFromAPI() {

        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                Log.w("CategorieActivity","response : $it")
                handleAPIData(it.toString())
            },
            {
                Log.e("FoodActivity", "erreur lors de la récupération de la liste des plats : $it")
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String){
        val dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == category }

        val adapter = binding.categoryList.adapter as DishAdapter
        adapter.refreshList(dishCategory?.items as ArrayList<Items> )
    }
}
