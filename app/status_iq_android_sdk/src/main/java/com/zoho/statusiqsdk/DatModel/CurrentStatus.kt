package com.zoho.statusiqsdk.DatModel

internal data class CurrentStatus(
    val component_status: Int,
    val componentgroup_components: List<ComponentgroupComponent>,
    val componentgroup_display_name: String,
    val componentgroup_status: Int,
    val desc: String,
    val display_name: String,
    val enc_component_id: String,
    val enc_componentgroup_id: String,
    val last_polled_time: String,
    val site24x7_monitor_type: String,
    var isExpanded:Boolean=false
)