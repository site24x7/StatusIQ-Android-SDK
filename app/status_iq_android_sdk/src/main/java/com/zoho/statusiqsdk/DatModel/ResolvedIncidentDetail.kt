package com.zoho.statusiqsdk.DatModel

internal data class ResolvedIncidentDetail(
    val grp_by_date: String,
    val grp_by_incidents_count: Int,
    val grp_by_incidents_list: List<GrpByIncidents>,
    val grp_by_status_msg: String
)