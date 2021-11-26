package com.zoho.statusiqsdk.DatModel

internal data class IncidentStatusUpdateX(
    val incident_status: String,
    val incident_status_desc: String,
    val incident_status_id: Int,
    val status_updated_at: String
)