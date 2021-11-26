package com.zoho.statusiqsdk.Fragments

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoho.statusiqsdk.ActiveIncidentsListAdapter
import com.zoho.statusiqsdk.Adapter.ComponentSummaryListAdapter
import com.zoho.statusiqsdk.Constants
import com.zoho.statusiqsdk.DatModel.*
import com.zoho.statusiqsdk.R
import org.json.JSONObject

internal class StatusIqFragment() : Fragment(R.layout.fragment_status_iq) {

    private lateinit var activeIncidentsList: RecyclerView
    private lateinit var componentSummaryList: RecyclerView
    private lateinit var statusPageLinearLayout: LinearLayout
    private lateinit var labelActiveIncident: TextView
    private lateinit var labelComponentSummary: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activeIncidentsList = view.findViewById(R.id.active_incidents_list)
        componentSummaryList = view.findViewById(R.id.component_summary_list)
        statusPageLinearLayout = view.findViewById(R.id.ll_status_page)
        activeIncidentsList.layoutManager = LinearLayoutManager(activity)
        labelActiveIncident = view.findViewById(R.id.label_active_incident)
        labelComponentSummary = view.findViewById(R.id.label_component_summary)


        val baseResponse = JSONObject(arguments?.getString(Constants.DATA))


        val activeIncidentJsonArray =
            baseResponse.optJSONArray(Constants.ACTIVE_INCIDENT_DETAILS)

        val activeIncidentList = ArrayList<ActiveIncidentDetail>()

        if (activeIncidentJsonArray.length() > 0) {

            for (i in 0..activeIncidentJsonArray.length() - 1) {

                val activeIncidentJsonObject = activeIncidentJsonArray.optJSONObject(i)

                val affectedComponentsJsonArray =
                    activeIncidentJsonObject.optJSONArray(Constants.INCIDENT_AFFECTED_COMPONENTS)

                val affectedComponentList = ArrayList<IncidentAffectedComponent>()

                for (j in 0..affectedComponentsJsonArray.length() - 1) {
                    val affectedComponent = affectedComponentsJsonArray.optJSONObject(j)
                    affectedComponentList.add(
                        IncidentAffectedComponent(
                            affectedComponent.optString(
                                Constants.COMPONENT_GROUP_DISPLAY_NAME
                            ), affectedComponent.optString(Constants.DISPLAY_NAME)
                        )
                    )
                }


                val incidentStatusUpdateJsonArray =
                    activeIncidentJsonObject.optJSONArray(Constants.INCIDENT_STATUS_UPDATES)

                val incidentStatusUpdateList = ArrayList<IncidentStatusUpdate>()


                for (k in 0..incidentStatusUpdateJsonArray.length() - 1) {
                    val incidentStatusUpdateJsonObject =
                        incidentStatusUpdateJsonArray.optJSONObject(k)
                    incidentStatusUpdateList.add(
                        IncidentStatusUpdate(
                            incidentStatusUpdateJsonObject.optString(
                                Constants.INCIDENT_STATUS
                            ),
                            incidentStatusUpdateJsonObject.optString(Constants.INCIDENT_STATUS_DESC),
                            incidentStatusUpdateJsonObject.optInt(Constants.INCIDENT_STATUS_ID),
                            incidentStatusUpdateJsonObject.optString(Constants.STATUS_UPDATED_AT)
                        )
                    )
                }


                val activeIncident = ActiveIncidentDetail(
                    activeIncidentJsonObject.optString(Constants.ENC_INC_ID),
                    affectedComponentList,
                    activeIncidentJsonObject.optString(Constants.INCIDENT_BEGAN_AT),
                    activeIncidentJsonObject.optString(Constants.INCIDENT_DESCRIPTION),
                    activeIncidentJsonObject.optString(Constants.INCIDENT_SEVERITY),
                    activeIncidentJsonObject.optInt(Constants.INCIDENT_SEVERITY_ID),
                    incidentStatusUpdateList,
                    activeIncidentJsonObject.optString(Constants.INCIDENT_TITLE),
                    activeIncidentJsonObject.optInt(Constants.INCIDENT_TYPE),

                    )

                activeIncidentList.add(activeIncident)

                activeIncidentsList.adapter =
                    ActiveIncidentsListAdapter(
                        activeIncidentList
                    )
            }
        } else {
            labelActiveIncident.visibility = View.GONE
            activeIncidentsList.visibility = View.GONE
        }

        componentSummaryList.layoutManager = LinearLayoutManager(activity)


        val currentStatusJsonArray = baseResponse.optJSONArray(Constants.CURRENT_STATUS)


        if (currentStatusJsonArray.length() > 0) {

            val listCurrentStatus = ArrayList<CurrentStatus>()

            for (k in 0..currentStatusJsonArray.length() - 1) {

                val currenStatusJsonObject = currentStatusJsonArray.optJSONObject(k)

                val componentGroupArray =
                    currenStatusJsonObject.optJSONArray(Constants.componentgroup_components)

                val componentGroupList = ArrayList<ComponentgroupComponent>()

                if (componentGroupArray != null && componentGroupArray.length() > 0) {
                    for (l in 0..componentGroupArray.length() - 1) {
                        val componentGroupJson = componentGroupArray.optJSONObject(l)

                        componentGroupList.add(
                            ComponentgroupComponent(
                                componentGroupJson.optInt(Constants.component_status),
                                componentGroupJson.optString(Constants.DISPLAY_NAME),
                                componentGroupJson.optString(Constants.enc_component_id),
                                componentGroupJson.optString(Constants.enc_componentgroup_id),
                                componentGroupJson.optString(Constants.last_polled_time),
                                componentGroupJson.optString(Constants.site24x7_monitor_type)
                            )
                        )
                    }
                }

                listCurrentStatus.add(
                    CurrentStatus(
                        currenStatusJsonObject.optInt(Constants.component_status),
                        componentGroupList,
                        currenStatusJsonObject.optString(Constants.componentgroup_display_name),
                        currenStatusJsonObject.optInt(Constants.componentgroup_status),
                        currenStatusJsonObject.optString(Constants.desc),
                        currenStatusJsonObject.optString(Constants.DISPLAY_NAME),
                        currenStatusJsonObject.optString(Constants.enc_component_id),
                        currenStatusJsonObject.optString(Constants.enc_componentgroup_id),
                        currenStatusJsonObject.optString(Constants.last_polled_time),
                        currenStatusJsonObject.optString(Constants.site24x7_monitor_type)
                    )
                )

            }


            componentSummaryList.adapter =
                ComponentSummaryListAdapter(
                    listCurrentStatus
                )
        } else {
            labelComponentSummary.visibility = View.GONE
            componentSummaryList.visibility = View.GONE
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.incident_history).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }

}



