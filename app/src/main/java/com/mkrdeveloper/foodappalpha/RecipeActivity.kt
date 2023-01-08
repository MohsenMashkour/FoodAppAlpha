package com.mkrdeveloper.foodappalpha

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val tv_recipe = findViewById<TextView>(R.id.tv_recipe_recipeActivity)
        val tv_name = findViewById<TextView>(R.id.tv_name_recipeActivity)
        val img_recipeActivity = findViewById<ImageView>(R.id.img_recipeActivity)


        val intent = intent
        val instructions = intent.getStringExtra("instructions")
        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")

        tv_recipe.text = instructions
        tv_name.text = title

        Picasso.get().load(image).into(img_recipeActivity)


        //Toast.makeText(this," $instructions",Toast.LENGTH_SHORT).show()
    }
}