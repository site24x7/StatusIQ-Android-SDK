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

internal data class ResolvedIncidentDetail(
    val grp_by_date: String,
    val grp_by_incidents_count: Int,
    val grp_by_incidents_list: List<GrpByIncidents>,
    val grp_by_status_msg: String
)