package com.mkrdeveloper.foodappalpha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.R
import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesItem
import com.squareup.picasso.Picasso

class SimilarRecAdapter(private val similarRecipesArrayList: ArrayList<SimilarRecipesItem>) :
    RecyclerView.Adapter<SimilarRecAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgSimilar: ImageView = itemView.findViewById(R.id.imgSimilarRecipe)

        val tvSimilarName: TextView = itemView.findViewById(R.id.tvSimilarFoodName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_similar_food_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return similarRecipesArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = similarRecipesArrayList[position]


        Picasso.get()
            .load("https://spoonacular.com/recipeImages/${currentItem.id}-556x370.${currentItem.imageType}")
            .into(holder.imgSimilar)
        holder.tvSimilarName.text = currentItem.title
        holder.tvSimilarName.isSelected = true
    }
}