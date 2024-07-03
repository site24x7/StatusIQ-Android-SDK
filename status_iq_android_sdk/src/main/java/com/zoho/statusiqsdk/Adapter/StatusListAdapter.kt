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


package com.zoho.statusiqsdk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoho.statusiqsdk.DatModel.ActiveIncidentDetail
import com.zoho.statusiqsdk.DatModel.IncidentStatusUpdate
import com.zoho.statusiqsdk.Utils.Util
import com.zoho.statusiqsdkapp.R
import java.util.*

internal class ActiveIncidentsListAdapter(
    val activeIncidentsList: List<ActiveIncidentDetail>?
) :
    RecyclerView.Adapter<ActiveIncidentsListAdapter.ActiveIncidentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveIncidentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.active_incident_list_item, parent, false)
        return ActiveIncidentViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ActiveIncidentViewHolder, position: Int) {

        holder.incidentName.setText(activeIncidentsList?.get(position)?.incident_title)

        holder.incidentName.setBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                Util.getStatusColorFromStatusCode(activeIncidentsList?.get(position)?.incident_severity_id)
            )
        )

        holder.incidentDescription.setText(
            holder.itemView.context.getString(R.string.this_incident_has_been_open_for) + " " + Util.calculateTimeDifference(
                activeIncidentsList?.get(position)?.incident_began_at,
                Date()
            )
        )
        holder.incidentStartTime.setText(Util.formatDateTime(activeIncidentsList?.get(position)?.incident_began_at))

        var affectedComponent = ""
        activeIncidentsList?.get(position)?.incident_affected_components?.forEachIndexed { index, it ->

            if (index > 0) {
                affectedComponent = affectedComponent + ", "
            }

            affectedComponent = affectedComponent + it.display_name

            if (!it.componentgroup_display_name.isNullOrEmpty()) {
                affectedComponent = affectedComponent + "(" + it.componentgroup_display_name + ")"
            }

        }
        holder.incidentStatusUpdatesList.layoutManager =
            LinearLayoutManager(holder.itemView.context)

        holder.incidentStatusUpdatesList.adapter =
            activeIncidentsList?.get(position)?.incident_status_updates?.let {
                IncidentStatusUpdatesAdapter(
                    it
                )
            }


        activeIncidentsList?.get(position)?.incident_status_updates?.forEach {

            it.incident_status
            it.incident_status_desc
            it.status_updated_at
        }



        holder.affectedComponent.setText(affectedComponent)

        holder.showMoreTextView.setOnClickListener {
            if (holder.collapsableView.visibility == View.GONE) {
                holder.collapsableView.visibility = View.VISIBLE
                holder.showMoreTextView.setText(holder.itemView.context.getString(R.string.less))
            } else {
                holder.collapsableView.visibility = View.GONE
                holder.showMoreTextView.setText(holder.itemView.context.getString(R.string.show_more))
            }
        }

    }

    override fun getItemCount(): Int {
        if (activeIncidentsList.isNullOrEmpty()) {
            return 0;
        } else {
            return activeIncidentsList.size
        }
    }

    inner class ActiveIncidentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val incidentName = view.findViewById<TextView>(R.id.tv_incident_name)
        val incidentDescription = view.findViewById<TextView>(R.id.tv_incident_description)
        val incidentStartTime = view.findViewById<TextView>(R.id.tv_incident_start_time)
        val affectedComponent = view.findViewById<TextView>(R.id.tv_affected_components)
        val collapsableView = view.findViewById<LinearLayout>(R.id.ll_collapsable_view)
        val showMoreTextView = view.findViewById<TextView>(R.id.tv_show_more)
        val incidentStatusUpdatesList =
            view.findViewById<RecyclerView>(R.id.incident_status_updates_list)
    }


}


internal class IncidentStatusUpdatesAdapter(val incidentStatusUpdatesList: List<IncidentStatusUpdate>) :
    RecyclerView.Adapter<IncidentStatusUpdatesAdapter.IncidentStatusUpdatesViewHolder>() {

    inner class IncidentStatusUpdatesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgStatus = view.findViewById<ImageView>(R.id.img_incident_status)
        val incidentStatus = view.findViewById<TextView>(R.id.tv_incident_status)
        val incidentStatusDescription = view.findViewById<TextView>(R.id.tv_status_description)
        val incidentReportedTime = view.findViewById<TextView>(R.id.tv_incident_reported_time)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IncidentStatusUpdatesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.incident_status_updates_list_item, parent, false)
        return IncidentStatusUpdatesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IncidentStatusUpdatesViewHolder, position: Int) {
        holder.incidentStatus.setText(incidentStatusUpdatesList.get(position).incident_status)
        holder.incidentStatusDescription.setText(incidentStatusUpdatesList.get(position).incident_status_desc)
        holder.incidentReportedTime.setText(
            holder.itemView.context.getString(R.string.posted_on) + " " + Util.formatDateTime(
                incidentStatusUpdatesList.get(
                    position
                ).status_updated_at
            )
        )

        holder.imgStatus.setImageResource(Util.getImgSrcFromStatusCode(incidentStatusUpdatesList.get(position).incident_status_id))


    }

    override fun getItemCount(): Int {
        return incidentStatusUpdatesList.size
    }
}