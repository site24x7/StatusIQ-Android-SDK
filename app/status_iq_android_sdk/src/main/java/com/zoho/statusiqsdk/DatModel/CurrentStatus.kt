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