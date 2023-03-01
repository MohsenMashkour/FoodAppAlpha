package com.mkrdeveloper.foodappalpha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.R
import com.mkrdeveloper.foodappalpha.listeners.CategoryListener

class RvCatAdapter(categoryListener: CategoryListener) : RecyclerView.Adapter<RvCatAdapter.ViewHolder>() {

    private val listener = categoryListener
    private val catList = arrayListOf(
        "Main course",
        "Side dish",
        "Appetizer",
        "salad",
        "Bread",
        "Breakfast",
        "Soup",
        "Beverage",
        "Sauce",
        "Marinade",
        "Dessert",
        "Finger food",
        "Snack",
        "Drink"
    )
    private val photoList = arrayListOf(
        R.drawable.main_course,
        R.drawable.side_dish,
        R.drawable.appetizer,
        R.drawable.salad,
        R.drawable.bread,
        R.drawable.breakfast,
        R.drawable.soup,
        R.drawable.drink,
        R.drawable.sauce,
        R.drawable.marinade,
        R.drawable.dessert,
        R.drawable.finger_food,
        R.drawable.snack,
        R.drawable.drink

    )

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_cat_item, parent, false)
        return ViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.tvIngName).text = catList[position]
        holder.itemView.findViewById<ImageView>(R.id.imgIngRecipe).setImageResource(photoList[position])
        holder.itemView.findViewById<ConstraintLayout>(R.id.categoryContainer).setOnClickListener {

            listener.categoryTag(position)
        }
    }


}