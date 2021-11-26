package com.zoho.statusiqsdk.Fragments

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zoho.statusiqsdk.Constants
import com.zoho.statusiqsdk.R
import com.zoho.statusiqsdk.Utils.Util
import org.json.JSONObject
import java.lang.String
import java.util.*

internal class SingleComponentFragment() : Fragment(R.layout.fragment_single_component) {

    lateinit var tvComponentName: TextView
    lateinit var tvLastChecked: TextView
    lateinit var tvUptime: TextView
    lateinit var tvIncidentCount: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvComponentName = view.findViewById(R.id.tv_component_name)

        tvLastChecked = view.findViewById(R.id.tv_last_checked)

        tvUptime = view.findViewById(R.id.tv_up_time)

        tvIncidentCount = view.findViewById(R.id.tv_incident_count)

        val response= JSONObject(arguments?.getString(Constants.DATA))

        tvComponentName.setText(response.optString(Constants.DISPLAY_NAME))

        tvIncidentCount.setText(response.optString(Constants.TOTAL_INCIDENT_COUNT))

        tvUptime.setText(response.optString(Constants.uptime_perc))

        tvLastChecked.setText(
            String.format(
                getString(R.string.last_checked_ago),
                Util.calculateTimeDifference(
                    arguments?.getString(Constants.last_polled_time),
                    Date()
                )
            )
        )

        val dateWiseStatusHistoryJsonArray = response.optJSONObject(Constants.status_history)
            ?.optJSONArray(Constants.day_wise_status_history)

        if (dateWiseStatusHistoryJsonArray != null && dateWiseStatusHistoryJsonArray.length() > 0) {


            view.findViewById<TextView>(R.id.tv_date_one).setText(
                Util.formatOnlyDate(
                    dateWiseStatusHistoryJsonArray.optJSONObject(0).optString(Constants.date)
                )
            )



            view.findViewById<ImageView>(R.id.img_status_one).setImageResource(
                Util.getStatusImageFromStatusCode(
                    dateWiseStatusHistoryJsonArray.optJSONObject(0).optInt(Constants.status)
                )
            )

            view.findViewById<ImageView>(R.id.img_status_two).setImageResource(
                Util.getStatusImageFromStatusCode(
                    dateWiseStatusHistoryJsonArray.optJSONObject(1).optInt(Constants.status)
                )
            )

            view.findViewById<ImageView>(R.id.img_status_three).setImageResource(
                Util.getStatusImageFromStatusCode(
                    dateWiseStatusHistoryJsonArray.optJSONObject(2).optInt(Constants.status)
                )
            )

            view.findViewById<ImageView>(R.id.img_status_four).setImageResource(
                Util.getStatusImageFromStatusCode(
                    dateWiseStatusHistoryJsonArray.optJSONObject(3).optInt(Constants.status)
                )
            )

            view.findViewById<ImageView>(R.id.img_status_five).setImageResource(
                Util.getStatusImageFromStatusCode(
                    dateWiseStatusHistoryJsonArray.optJSONObject(4).optInt(Constants.status)
                )
            )

            view.findViewById<ImageView>(R.id.img_status_six).setImageResource(
                Util.getStatusImageFromStatusCode(
                    dateWiseStatusHistoryJsonArray.optJSONObject(5).optInt(Constants.status)
                )
            )

            view.findViewById<ImageView>(R.id.img_status_seven).setImageResource(
                Util.getStatusImageFromStatusCode(
                    dateWiseStatusHistoryJsonArray.optJSONObject(6).optInt(Constants.status)
                )
            )


            view.findViewById<TextView>(R.id.tv_date_two).setText(
                Util.formatOnlyDate(
                    dateWiseStatusHistoryJsonArray.optJSONObject(1).optString(Constants.date)
                )
            )

            view.findViewById<TextView>(R.id.tv_date_three)
                .setText(
                    Util.formatOnlyDate(
                        dateWiseStatusHistoryJsonArray.optJSONObject(2).optString(Constants.date)
                    )
                )

            view.findViewById<TextView>(R.id.tv_date_four)
                .setText(
                    Util.formatOnlyDate(
                        dateWiseStatusHistoryJsonArray.optJSONObject(3).optString(Constants.date)
                    )
                )

            view.findViewById<TextView>(R.id.tv_date_five)
                .setText(
                    Util.formatOnlyDate(
                        dateWiseStatusHistoryJsonArray.optJSONObject(4).optString(Constants.date)
                    )
                )

            view.findViewById<TextView>(R.id.tv_date_six)
                .setText(
                    Util.formatOnlyDate(
                        dateWiseStatusHistoryJsonArray.optJSONObject(5).optString(Constants.date)
                    )
                )

            view.findViewById<TextView>(R.id.tv_date_seven)
                .setText(
                    Util.formatOnlyDate(
                        dateWiseStatusHistoryJsonArray.optJSONObject(6).optString(Constants.date)
                    )
                )

        }

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