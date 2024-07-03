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


package com.zoho.statusiqsdk.Fragments

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoho.statusiqsdk.Adapter.IncidentHistoryListAdapter
import com.zoho.statusiqsdk.Constants
import com.zoho.statusiqsdk.DatModel.*
import com.zoho.statusiqsdk.StatusIQActivity
import com.zoho.statusiqsdkapp.R
import org.json.JSONObject

internal class IncidentHistoryFragment() : Fragment(R.layout.fragment_incident_history) {

    lateinit var incidentHistoryList: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as StatusIQActivity).setActionBarTitle(getString(R.string.incident_history))

        incidentHistoryList = view.findViewById(R.id.incident_history_list)

        incidentHistoryList.layoutManager = LinearLayoutManager(activity)

        val baseResponse= JSONObject(arguments?.getString(Constants.DATA))

        val resolvedIncidentJsonArray = baseResponse.optJSONArray(Constants.RESOLVED_INCIDENT_DETAILS)


        val resolvedIncidentList = ArrayList<ResolvedIncidentDetail>()

        for (i in 0..resolvedIncidentJsonArray.length() - 1) {

            val resolvedIncidentJsonObject = resolvedIncidentJsonArray.optJSONObject(i)

            val grpaByincidentListJsonArray = resolvedIncidentJsonObject.optJSONArray(Constants.grp_by_incidents_list)

            val grpByIncidentsList = ArrayList<GrpByIncidents>()

            if (grpaByincidentListJsonArray!=null && grpaByincidentListJsonArray.length()>0){
                for (j in 0..grpaByincidentListJsonArray.length() - 1) {
                    val grpByIncident = grpaByincidentListJsonArray.optJSONObject(j)

                    val incidentAffectedComponetJsonArray =
                        grpByIncident.optJSONArray(Constants.INCIDENT_AFFECTED_COMPONENTS)

                    val incidentAffectedComponentList = ArrayList<IncidentAffectedComponentX>()

                    for (l in 0..incidentAffectedComponetJsonArray.length() - 1) {

                        incidentAffectedComponentList.add(IncidentAffectedComponentX(
                            incidentAffectedComponetJsonArray.optJSONObject(l)
                                .optString(Constants.DISPLAY_NAME)
                        )
                        )
                    }


                    val incidentStatusUpdateJsonArray =
                        grpByIncident.optJSONArray(Constants.INCIDENT_STATUS_UPDATES)

                    val incidentStatusUpdateList = ArrayList<IncidentStatusUpdateX>()


                    for (k in 0..incidentStatusUpdateJsonArray.length() - 1) {
                        val incidentStatusUpdateJsonObject =
                            incidentStatusUpdateJsonArray.optJSONObject(k)

                        incidentStatusUpdateList.add(
                            IncidentStatusUpdateX(
                                incidentStatusUpdateJsonObject.optString(
                                    Constants.INCIDENT_STATUS
                                ),
                                incidentStatusUpdateJsonObject.optString(Constants.INCIDENT_STATUS_DESC),
                                incidentStatusUpdateJsonObject.optInt(Constants.INCIDENT_STATUS_ID),
                                incidentStatusUpdateJsonObject.optString(Constants.STATUS_UPDATED_AT)
                            )
                        )
                    }


                    grpByIncidentsList.add(
                        GrpByIncidents(
                            grpByIncident.optString(Constants.ENC_INC_ID),
                            incidentAffectedComponentList,
                            grpByIncident.optString(Constants.INCIDENT_BEGAN_AT),
                            grpByIncident.optString(Constants.INCIDENT_DESCRIPTION),
                            grpByIncident.optString(Constants.incident_ended_at),
                            grpByIncident.optString(Constants.INCIDENT_SEVERITY),
                            grpByIncident.optInt(Constants.INCIDENT_SEVERITY_ID),
                            incidentStatusUpdateList,
                            grpByIncident.optString(Constants.INCIDENT_TITLE),
                            grpByIncident.optInt(Constants.INCIDENT_TYPE),

                            )
                    )

                }
            }


            val activeIncident = ResolvedIncidentDetail(
                resolvedIncidentJsonObject.optString(Constants.grp_by_date),
                resolvedIncidentJsonObject.optInt(Constants.grp_by_incidents_count),
                grpByIncidentsList,
                resolvedIncidentJsonObject.optString(Constants.grp_by_status_msg),
            )

            resolvedIncidentList.add(activeIncident)

        }

        incidentHistoryList.adapter = IncidentHistoryListAdapter(resolvedIncidentList)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.incident_history).setVisible(false)
        super.onPrepareOptionsMenu(menu)
    }
}