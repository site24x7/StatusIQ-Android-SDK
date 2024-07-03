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



package com.zoho.statusiqsdk.Utils

import com.zoho.statusiqsdkapp.R
import java.text.SimpleDateFormat
import java.util.*


internal class Util {

    companion object {

        fun formatDateTime(date: String?): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())

            return SimpleDateFormat(
                "MMM dd,yyyy 'at' hh:mm aaa z",
                Locale.getDefault()
            ).format(dateFormat.parse(date))

        }

        fun formatDate(dateString: String?): String {
            val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
            val outputDateFormat = SimpleDateFormat("MMM dd,yyyy", Locale.getDefault())

            val dateObject = inputDateFormat.parse(dateString)

            return outputDateFormat.format(dateObject)
        }


        fun formatOnlyDate(date: String?): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())

            return SimpleDateFormat("MMM dd", Locale.getDefault()).format(dateFormat.parse(date))
        }


        fun calculateTimeDifference(startDate: String?, endDate: Date): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())

            val startDateObject: Date = dateFormat.parse(startDate)

            var different = endDate.time - startDateObject.time
            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val hoursInMilli = minutesInMilli * 60
            val daysInMilli = hoursInMilli * 24
            val elapsedDays = different / daysInMilli
            different = different % daysInMilli
            val elapsedHours = different / hoursInMilli
            different = different % hoursInMilli
            val elapsedMinutes = different / minutesInMilli

            var difference = ""

            if (elapsedDays != 0L) {
                difference = difference + elapsedDays.toString() + " days"
            }

            if (elapsedHours != 0L) {
                difference = difference + elapsedHours.toString() + " hours"
            }

            if (elapsedMinutes != 0L) {
                difference = difference + elapsedMinutes.toString() + " mins"
            }

            return difference

        }

        fun getStatusImageFromStatusCode(statusCode: Int?): Int {

            when (statusCode) {

                1 -> {
                    return R.drawable.status_iq_operational
                }
                2 -> {
                    return R.drawable.status_iq_informational
                }
                3 -> {
                    return R.drawable.status_iq_degraded_performance
                }
                4 -> {
                    return R.drawable.status_iq_under_maintanance
                }
                5 -> {
                    return R.drawable.status_iq_partial_outage
                }

                6 -> {
                    return R.drawable.status_iq_major_outage
                }
                else -> {
                    return R.drawable.status_iq_operational
                }

            }

        }


        fun getStatusColorFromStatusCode(statusCode: Int?): Int {

            when (statusCode) {

                1 -> {
                    return R.color.status_iq_operational_bg
                }
                2 -> {
                    return R.color.status_iq_informational
                }
                3 -> {
                    return R.color.status_iq_degraded_bg
                }
                4 -> {
                    return R.color.status_iq_maintenance_bg
                }
                5 -> {
                    return R.color.status_iq_partial_outage_bg
                }

                6 -> {
                    return R.color.status_iq_outage_bg
                }
                else -> {
                    return R.color.status_iq_operational_bg
                }

            }

        }


        fun getImgSrcFromStatusCode(statusCode: Int?): Int {

            when (statusCode) {

                10 -> {
                    return R.drawable.status_iq_acknowledged
                }

                11 -> {
                    return R.drawable.status_iq_investigating
                }

                12 -> {
                    return R.drawable.status_iq_identified
                }

                13 -> {
                    return R.drawable.status_iq_monitoring
                }

                14 -> {
                    return R.drawable.status_iq_resolved
                }


                21 -> {
                    return R.drawable.status_iq_scheduled
                }


                22 -> {
                    return R.drawable.status_iq_in_progress
                }

                23 -> {
                    return R.drawable.status_iq_monitoring
                }

                24 -> {
                    return R.drawable.status_iq_resolved
                }


                else -> {
                    return R.drawable.status_iq_acknowledged
                }

            }

        }

    }

}