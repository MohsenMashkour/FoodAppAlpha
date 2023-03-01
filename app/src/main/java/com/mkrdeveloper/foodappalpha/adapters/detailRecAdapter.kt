package com.mkrdeveloper.foodappalpha.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.R
import com.mkrdeveloper.foodappalpha.models.ExtendedIngredient
import com.squareup.picasso.Picasso

class detailRecAdapter(private var ingredients : ArrayList<ExtendedIngredient>) : RecyclerView.Adapter<detailRecAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgIng: ImageView = itemView.findViewById(R.id.imgIngRecipe)

        val tvIngName: TextView = itemView.findViewById(R.id.tvIngName)
        val tvIngAmount: TextView = itemView.findViewById(R.id.tvIngAmount)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_detail_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = ingredients[position]


        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + currentItem.image).into(holder.imgIng)
        holder.tvIngName.text = currentItem.name
        holder.tvIngAmount.text = currentItem.original
        holder.tvIngAmount.isSelected = true
       // holder.tvIngName.isSelected = true

        //Toast.makeText(holder.tvName.context, currentItem.image, Toast.LENGTH_SHORT).show()
       // Log.d("adapterrrr", currentItem.title)

    }


}