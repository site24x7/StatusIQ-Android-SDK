package com.zoho.statusiqsdk.DatModel

internal data class GrpByIncidents(
    val enc_inc_id: String,
    val incident_affected_components: List<IncidentAffectedComponentX>,
    val incident_began_at: String,
    val incident_desc: String,
    val incident_ended_at: String,
    val incident_severity: String,
    val incident_severity_id: Int,
    val incident_status_updates: List<IncidentStatusUpdateX>,
    val incident_title: String,
    val incident_type: Int,
)