package com.mkrdeveloper.foodappalpha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.R
import com.mkrdeveloper.foodappalpha.models.recipesSteps.Ingredient
import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesItem
import com.squareup.picasso.Picasso

class RvStepIngAdapter(private val stepIngList: List<Ingredient>) :
    RecyclerView.Adapter<RvStepIngAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgStepIng: ImageView = itemView.findViewById(R.id.imgStepIng)

        val tvStepIngName: TextView = itemView.findViewById(R.id.tvStepIngName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_step_ingredient_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stepIngList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = stepIngList[position]


        Picasso.get()
            .load("https://spoonacular.com/cdn/ingredients_100x100/${currentItem.image}")
            .into(holder.imgStepIng)
        holder.tvStepIngName.text = currentItem.name
        holder.tvStepIngName.isSelected = true
    }
}