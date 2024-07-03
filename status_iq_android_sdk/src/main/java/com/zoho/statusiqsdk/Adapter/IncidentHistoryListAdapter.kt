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
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoho.statusiqsdk.DatModel.GrpByIncidents
import com.zoho.statusiqsdk.DatModel.IncidentStatusUpdateX
import com.zoho.statusiqsdk.DatModel.ResolvedIncidentDetail
import com.zoho.statusiqsdk.Utils.Util
import com.zoho.statusiqsdkapp.R

internal class IncidentHistoryListAdapter(val resolvedIncidentList: List<ResolvedIncidentDetail>?) :
    RecyclerView.Adapter<IncidentHistoryListAdapter.IncidentHistoryListViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IncidentHistoryListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.incident_history_list_item, parent, false)

        return IncidentHistoryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentHistoryListViewHolder, position: Int) {

        val incident = resolvedIncidentList?.get(position)

        holder.tvDate.text = Util.formatDate(incident?.grp_by_date)

        if (incident?.grp_by_incidents_list.isNullOrEmpty()) {
            holder.tvNoIncident.visibility = View.VISIBLE
            holder.incidentList.visibility = View.GONE
            holder.seperationView.visibility=View.VISIBLE
        } else {
            holder.tvNoIncident.visibility = View.GONE
            holder.incidentList.visibility = View.VISIBLE
            holder.incidentList.layoutManager = LinearLayoutManager(holder.itemView.context)
            holder.incidentList.adapter = IncidentListAdapter(incident?.grp_by_incidents_list)
            holder.seperationView.visibility=View.GONE

        }

    }

    override fun getItemCount(): Int {
        if (resolvedIncidentList.isNullOrEmpty()) {
            return 0
        } else {
            return resolvedIncidentList.size
        }
    }


    inner class IncidentHistoryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        val incidentList = view.findViewById<RecyclerView>(R.id.incident_list)
        val tvNoIncident = view.findViewById<TextView>(R.id.tv_no_incident)
        val seperationView=view.findViewById<View>(R.id.seperation_view)

    }

}


internal class IncidentListAdapter(val incidentList: List<GrpByIncidents>?) :
    RecyclerView.Adapter<IncidentListAdapter.IncidentListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.incident_history_inner_item_one, parent, false)

        return IncidentListViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentListViewHolder, position: Int) {

        val incident = incidentList?.get(position)

        holder.tvIncidentName.text = incident?.incident_title

        holder.tvIncidentName.setTextColor(
            ContextCompat.getColor(
                holder.itemView.context,
                Util.getStatusColorFromStatusCode(incident?.incident_severity_id)
            )
        )


        val incidentDuration =
            "From " + Util.formatDateTime(incident?.incident_began_at) + " To " + Util.formatDateTime(
                incident?.incident_ended_at
            )

        holder.tvIncidentDuration.text = incidentDuration

        var affectedComponent = ""
        incident?.incident_affected_components?.forEachIndexed { index, it ->

            if (index > 0) {
                affectedComponent = affectedComponent + ", "
            }

            affectedComponent = affectedComponent + it.display_name


        }

        holder.tvAffectedComponents.text = affectedComponent


        holder.incidentStatusUpdateList.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.incidentStatusUpdateList.adapter =
            IncidentStatusUpdatesAdapter(incident?.incident_status_updates)


    }

    override fun getItemCount(): Int {
        if (incidentList.isNullOrEmpty()) {
            return 0
        } else {
            return incidentList.size
        }
    }


    inner class IncidentListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIncidentName = view.findViewById<TextView>(R.id.tv_incident_name)
        val tvIncidentDuration = view.findViewById<TextView>(R.id.tv_incident_duration)
        val tvAffectedComponents = view.findViewById<TextView>(R.id.tv_affected_components)
        val incidentStatusUpdateList =
            view.findViewById<RecyclerView>(R.id.incident_status_updates_list)

    }


}


internal class IncidentStatusUpdatesAdapter(val incidentStatusUpdatesList: List<IncidentStatusUpdateX>?) :
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
        holder.incidentStatus.setText(incidentStatusUpdatesList?.get(position)?.incident_status)
        holder.incidentStatusDescription.setText(incidentStatusUpdatesList?.get(position)?.incident_status_desc)
        holder.incidentReportedTime.setText(
            holder.itemView.context.getString(R.string.posted_on) + " " + Util.formatDateTime(
                incidentStatusUpdatesList?.get(
                    position
                )?.status_updated_at
            )
        )


        holder.imgStatus.setImageResource(Util.getImgSrcFromStatusCode(incidentStatusUpdatesList?.get(position)?.incident_status_id))
    }

    override fun getItemCount(): Int {
        if (incidentStatusUpdatesList.isNullOrEmpty()) {
            return 0
        } else {
            return incidentStatusUpdatesList.size

        }
    }
}