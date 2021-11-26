package com.zoho.statusiqsdk.DatModel

internal data class ComponentgroupComponent(
    val component_status: Int,
    val display_name: String,
    val enc_component_id: String,
    val enc_componentgroup_id: String,
    val last_polled_time: String,
    val site24x7_monitor_type: String,
)