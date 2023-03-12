package com.mkrdeveloper.foodappalpha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.R
import com.mkrdeveloper.foodappalpha.models.recipesSteps.Equipment
import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesItem
import com.squareup.picasso.Picasso

class RvStepEqAdapter(private val stepEqList: List<Equipment>) :
    RecyclerView.Adapter<RvStepEqAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgStepEq: ImageView = itemView.findViewById(R.id.imgStepEq)

        val tvSimilarName: TextView = itemView.findViewById(R.id.tvStepEqName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_step_equipment_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stepEqList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = stepEqList[position]


        Picasso.get()
            .load("https://spoonacular.com/cdn/equipment_100x100/${currentItem.image}")
            .into(holder.imgStepEq)
        holder.tvSimilarName.text = currentItem.name
        holder.tvSimilarName.isSelected = true
    }
}