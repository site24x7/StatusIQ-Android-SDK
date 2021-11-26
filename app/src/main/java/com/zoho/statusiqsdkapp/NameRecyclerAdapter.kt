package com.zoho.statusiqsdkapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameRecyclerAdapter(val nameList: List<String>, val onClickListener: MainActivity): RecyclerView.Adapter<NameRecyclerAdapter.NameRecyclerViewHolder>() {


    inner class NameRecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){

       val tvName= view.findViewById<TextView>(R.id.tv_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sliding_layout_item_single, parent, false)
        return NameRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameRecyclerViewHolder, position: Int) {
       holder.tvName.text=(nameList.get(position))

        holder.tvName.setOnClickListener {
            onClickListener.onClick(position,nameList.get(position))
        }

    }

    override fun getItemCount(): Int {
       return nameList.size
    }
}