package com.mkrdeveloper.foodappalpha

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.adapters.SimilarRecAdapter
import com.mkrdeveloper.foodappalpha.adapters.RvDetailAdapter
import com.mkrdeveloper.foodappalpha.listeners.DetailsRecipeResponseListener
import com.mkrdeveloper.foodappalpha.listeners.SimilarRecipeResponseListener
import com.mkrdeveloper.foodappalpha.models.DetailsOfRecipes
import com.mkrdeveloper.foodappalpha.models.ExtendedIngredient
import com.mkrdeveloper.foodappalpha.models.Step
import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesItem
import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeActivity : AppCompatActivity() {

    private lateinit var listener: DetailsRecipeResponseListener
    private lateinit var similarlistener: SimilarRecipeResponseListener
    private lateinit var recAdapter: RvDetailAdapter
    private lateinit var similarRecAdapter: SimilarRecAdapter
    private lateinit var recView: RecyclerView
    private lateinit var rVSimilarRecipes: RecyclerView
    private lateinit var rvSteps: RecyclerView
    private lateinit var itemArrayList: ArrayList<ExtendedIngredient>
    private lateinit var similarRecipesArrayList: ArrayList<SimilarRecipesItem>
    private lateinit var stepsArrayList: ArrayList<Step>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)



        val tvName = findViewById<TextView>(R.id.tv_name_recipeActivity)
        val imgRecipeActivity = findViewById<ImageView>(R.id.img_recipeActivity)

        recView = findViewById(R.id.rvDetails)
        recView.layoutManager =
            GridLayoutManager(this@RecipeActivity, 1, RecyclerView.HORIZONTAL, false)
        recView.setHasFixedSize(true)

        rVSimilarRecipes = findViewById(R.id.rvSimilarFoods)
        rVSimilarRecipes.layoutManager =
            GridLayoutManager(this@RecipeActivity, 1, RecyclerView.HORIZONTAL, false)
        rVSimilarRecipes.setHasFixedSize(true)

        rvSteps = findViewById(R.id.rv_recipe_recipeActivity)
        rvSteps.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@RecipeActivity)
        }

        val intent = intent
        val instructions = intent.getStringExtra("instructions")
        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val id = intent.getStringExtra("id")



        tvName.text = title

        Picasso.get().load(image).into(imgRecipeActivity)







        listener = object : DetailsRecipeResponseListener {


            override suspend fun onFetch(response: DetailsOfRecipes?, message: String) {
                if (response != null) {
                    itemArrayList = response.extendedIngredients as ArrayList<ExtendedIngredient>


                    // Toast.makeText(this@RecipeActivity," ${response.summary}",Toast.LENGTH_SHORT).show()


                    withContext(Dispatchers.Main) {
                        //tv_recipe.text = response.summary
                        recAdapter = RvDetailAdapter(itemArrayList)
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

        similarlistener = object : SimilarRecipeResponseListener {
            override suspend fun onFetch(response: SimilarRecipesResponse?, message: String) {
                if (response != null) {
                    similarRecipesArrayList =
                        response


                    withContext(Dispatchers.Main) {

                        similarRecAdapter = SimilarRecAdapter(similarRecipesArrayList)
                        rVSimilarRecipes.adapter = similarRecAdapter
                    }
                }
            }

            override fun onError(msg: String) {
                Toast.makeText(applicationContext, "error $msg", Toast.LENGTH_SHORT).show()
            }
        }
        if (id != null) {
            RequestManager(applicationContext, "", id.toInt()).getSimilarRecipes(similarlistener)
        }
    }
}