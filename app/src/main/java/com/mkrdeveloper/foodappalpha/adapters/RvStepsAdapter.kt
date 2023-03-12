package com.mkrdeveloper.foodappalpha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.R
import com.mkrdeveloper.foodappalpha.models.recipesSteps.Step

class RvStepsAdapter(private val stepsList: List<Step>) :
    RecyclerView.Adapter<RvStepsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvStepsNumber: TextView = itemView.findViewById(R.id.tv_step_number)
        val tvStepsDetail: TextView = itemView.findViewById(R.id.tv_step_detail)
        val rvStepsIng: RecyclerView = itemView.findViewById(R.id.rv_step_ingredients)
        val rvStepsEq: RecyclerView = itemView.findViewById(R.id.rv_step_equipments)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_steps_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return stepsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = stepsList[position]
        holder.tvStepsNumber.text = "Step ${currentItem.number}:"
        holder.tvStepsDetail.text = currentItem.step

        holder.rvStepsEq.apply {
            layoutManager = GridLayoutManager(holder.itemView.context,1,RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = RvStepEqAdapter(currentItem.equipment)
        }
        holder.rvStepsIng.apply {
            layoutManager = GridLayoutManager(holder.itemView.context,1,RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = RvStepIngAdapter(currentItem.ingredients)
        }

    }
}