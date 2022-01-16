/*Copyright (c) 2022, ZOHO CORPORATION PRIVATE LIMITED
All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/


package com.zoho.statusiqsdk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoho.statusiqsdk.DatModel.ComponentgroupComponent
import com.zoho.statusiqsdk.DatModel.CurrentStatus
import com.zoho.statusiqsdk.R
import com.zoho.statusiqsdk.Utils.Util

internal class ComponentSummaryListAdapter(val activeIncidentsList: List<CurrentStatus>?) :
    RecyclerView.Adapter<ComponentSummaryListAdapter.ComponentSummaryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComponentSummaryListAdapter.ComponentSummaryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.component_summary_list_item_single, parent, false)

        return ComponentSummaryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ComponentSummaryListAdapter.ComponentSummaryViewHolder,
        position: Int
    ) {
        val component = activeIncidentsList?.get(position)

        if (component?.display_name.isNullOrEmpty()) {

            holder.componentName.setText(component?.componentgroup_display_name)
            holder.imgExpand.visibility = View.VISIBLE
            holder.imgStatus.setImageResource(Util.getStatusImageFromStatusCode(component?.componentgroup_status))

            if (component?.isExpanded!!) {
                holder.subComponentList.visibility = View.VISIBLE
                holder.imgExpand.setImageResource(R.drawable.status_iq_arrow_up)
            } else {
                holder.imgExpand.setImageResource(R.drawable.status_iq_arrow_down)
                holder.subComponentList.visibility = View.GONE
            }

            holder.subComponentList.layoutManager = LinearLayoutManager(holder.subComponentList.context)
            holder.subComponentList.adapter = SubComponentListAdapter(component.componentgroup_components)


            holder.imgExpand.setOnClickListener {
                component.isExpanded = !component.isExpanded
                notifyDataSetChanged()
            }


        } else {
            holder.componentName.setText(component?.display_name)
            holder.imgExpand.visibility = View.INVISIBLE
            holder.subComponentList.visibility = View.GONE
            holder.imgStatus.setImageResource(Util.getStatusImageFromStatusCode(component?.component_status))

        }


    }

    override fun getItemCount(): Int {
        if (activeIncidentsList.isNullOrEmpty()) {
            return 0;
        } else {
            return activeIncidentsList.size
        }

    }

    inner class ComponentSummaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val componentName = view.findViewById<TextView>(R.id.tv_component_name)
        val imgExpand = view.findViewById<ImageView>(R.id.img_expand)
        val subComponentList = view.findViewById<RecyclerView>(R.id.sub_component_list)
        val imgStatus = view.findViewById<ImageView>(R.id.img_status)
    }


}

private class SubComponentListAdapter(val subComponentsList: List<ComponentgroupComponent>?) :
    RecyclerView.Adapter<SubComponentListAdapter.SubComponentListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubComponentListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sub_component_list_item, parent, false)
        return SubComponentListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubComponentListViewHolder, position: Int) {

        holder.subComponentName.text = subComponentsList?.get(position)?.display_name
        holder.imgStatus.setImageResource(
            Util.getStatusImageFromStatusCode(
                subComponentsList?.get(
                    position
                )?.component_status
            )
        )

    }

    override fun getItemCount(): Int {
        if (subComponentsList.isNullOrEmpty()) {
            return 0
        } else {
            return subComponentsList.size
        }
    }


    inner class SubComponentListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val subComponentName = view.findViewById<TextView>(R.id.tv_sub_component_name)
        val imgStatus = view.findViewById<ImageView>(R.id.img_status)


    }

}


