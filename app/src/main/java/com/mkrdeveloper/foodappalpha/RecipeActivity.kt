package com.mkrdeveloper.foodappalpha

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.adapters.detailRecAdapter
import com.mkrdeveloper.foodappalpha.adapters.recAdapter
import com.mkrdeveloper.foodappalpha.listeners.DetailsRecipeResponseListener
import com.mkrdeveloper.foodappalpha.models.DetailsOfRecipes
import com.mkrdeveloper.foodappalpha.models.ExtendedIngredient
import com.mkrdeveloper.foodappalpha.models.Recipes
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeActivity : AppCompatActivity() {

    private lateinit var listener: DetailsRecipeResponseListener
    private lateinit var recAdapter: detailRecAdapter
    private lateinit var recView: RecyclerView
    private lateinit var itemArrayList: ArrayList<ExtendedIngredient>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)


        val tv_recipe = findViewById<TextView>(R.id.tv_recipe_recipeActivity)
        val tv_name = findViewById<TextView>(R.id.tv_name_recipeActivity)
        val img_recipeActivity = findViewById<ImageView>(R.id.img_recipeActivity)

        recView = findViewById(R.id.rvDetails)
        recView.layoutManager = GridLayoutManager(this@RecipeActivity, 1,RecyclerView.HORIZONTAL,false)
        recView.setHasFixedSize(true)


        val intent = intent
        val instructions = intent.getStringExtra("instructions")
        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val id = intent.getStringExtra("id")

        tv_recipe.text = instructions
        tv_name.text = title

        Picasso.get().load(image).into(img_recipeActivity)






        listener = object : DetailsRecipeResponseListener {


            override suspend fun onFetch(response: DetailsOfRecipes?, message: String) {
                if (response != null) {
                    itemArrayList = response.extendedIngredients as ArrayList<ExtendedIngredient>


                    // Toast.makeText(this@RecipeActivity," ${response.summary}",Toast.LENGTH_SHORT).show()


                    withContext(Dispatchers.Main) {
                        //tv_recipe.text = response.summary
                        recAdapter = detailRecAdapter(itemArrayList)
                        recView.adapter = recAdapter
                    }
                }
            }

            override fun onError(msg: String) {
                Toast.makeText(applicationContext, "error $msg", Toast.LENGTH_SHORT).show()
            }
        }
        if (id != null) {
            RequestManager(applicationContext, "", id.toInt()).getDetailRecipes(listener)
        }
    }
}