package fr.isen.pecorella.androiderestaurant


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.pecorella.androiderestaurant.databinding.ActivityCategorieBinding
import org.json.JSONObject
import com.google.gson.Gson
import fr.isen.pecorella.androiderestaurant.modele.Items


class CategorieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategorieBinding
    private var itemsList = ArrayList<Items>()
    private lateinit var myCategoryAdapter : DishAdapter
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = intent.getStringExtra("categoryName") ?: ""
        this.title=category
        binding = ActivityCategorieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var menuName = intent.getStringExtra("categoryName") ?: ""
        var menuList = intent.getStringArrayListExtra("List_Meal")

        if (menuName != null && menuList != null) {
            supportActionBar?.title = menuName

            myCategoryAdapter = DishAdapter(itemsList){
                val intent = Intent(this@CategorieActivity, CategorieActivity::class.java)
                intent.putExtra("categoryName", "Entrées")
                startActivity(intent);
            }
            val layoutManager = LinearLayoutManager(applicationContext)
            binding.categoryList.layoutManager = layoutManager
            binding.categoryList.adapter = myCategoryAdapter
            binding.categoryList.layoutManager = LinearLayoutManager(this)
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
        var dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == category }

        val adapter = binding.categoryList.adapter as DishAdapter
        adapter.refreshList(dishCategory?.items as ArrayList<Items> )
    }
}
