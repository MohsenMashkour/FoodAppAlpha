package com.mkrdeveloper.foodappalpha.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.R
import com.mkrdeveloper.foodappalpha.RecipeActivity
import com.mkrdeveloper.foodappalpha.models.Recipes
import com.squareup.picasso.Picasso

class RvMainAdapter(private var recipes: ArrayList<Recipes>) :
    RecyclerView.Adapter<RvMainAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView = itemView.findViewById(R.id.img_item)
        val img_like = itemView.findViewById<ImageView>(R.id.img_like)
        val img_time = itemView.findViewById<ImageView>(R.id.img_time)
        val img_serving = itemView.findViewById<ImageView>(R.id.img_serving)

        val tvName: TextView = itemView.findViewById(R.id.tv_name_recipeActivity)
        val tv_time: TextView = itemView.findViewById(R.id.tv_time)
        val tv_like = itemView.findViewById<TextView>(R.id.tv_like)
        val tv_serving = itemView.findViewById<TextView>(R.id.tv_serving)
        val tv_resipe = itemView.findViewById<TextView>(R.id.tv_recipe)

        val cl_item: ConstraintLayout = itemView.findViewById(R.id.cl_item)
    }

    fun onSearchApplied(recipes: ArrayList<Recipes>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_main_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recipes[position]

        Picasso.get().load(currentItem.image).into(holder.imgItem)
        holder.tvName.text = currentItem.title
        holder.tv_time.text = "${currentItem.readyInMinutes} minutes"
        holder.tv_like.text = "${currentItem.aggregateLikes} likes"
        holder.tv_serving.text = "${currentItem.servings} servings"
        // holder.tv_resipe.text = currentItem.instructions


        holder.cl_item.setOnClickListener {
            val intent = Intent(it.context, RecipeActivity::class.java)
            intent.putExtra("instructions", currentItem.instructions)
            intent.putExtra("image", currentItem.image)
            intent.putExtra("title", currentItem.title)
            intent.putExtra("id", currentItem.id.toString())

            it.context.startActivity(intent)

        }
    }


}