package com.zoho.statusiqsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.zoho.statusiqsdk.Fragments.IncidentHistoryFragment
import com.zoho.statusiqsdk.Fragments.SingleComponentFragment
import com.zoho.statusiqsdk.Fragments.StatusIqFragment
import com.zoho.statusiqsdk.Utils.RetrofitFactory
import com.zoho.statusiqsdk.Utils.RetrofitService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


internal class StatusIQActivity : AppCompatActivity() {

    private lateinit var actionBarTitle: String
    private lateinit var statusIQTv: TextView
    private lateinit var statusIqBaseUrl: String
    private lateinit var progressBar: ProgressBar
    lateinit var baseResponse: JSONObject
    var singleComponentResponseBody: JSONObject? = null
    var isStatusPageAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {

        try {
            val themeResId = StatusIq.themeResId

            if (themeResId != null) {
                setTheme(themeResId)
            }
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_status_iq)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            actionBarTitle = intent.getStringExtra(Constants.ACTION_BAR_TITLE)!!


            setActionBarTitle(actionBarTitle)
            statusIQTv = findViewById(R.id.tv_status)
            progressBar = findViewById(R.id.pg_bar)

            val StatusIQ_resId = applicationContext.resources.getIdentifier(
                Constants.STATUS_IQ_URL,
                Constants.DEF_TYPE_STRING,
                applicationContext.packageName
            )


            if (StatusIQ_resId <= 0) {
                statusIQTv.visibility = View.VISIBLE
                statusIQTv.setText(R.string.status_Iq_Base_Url_Not_Found)
                return
            } else {
                statusIqBaseUrl = getString(StatusIQ_resId);

                progressBar.visibility = View.VISIBLE

                val service = RetrofitFactory.makeRetrofitService(statusIqBaseUrl)


                service.getStatusPageData().enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {

                        if (response.isSuccessful) {
                            baseResponse =
                                JSONObject(response.body().toString()).optJSONObject(Constants.DATA)


                            val showSingleComponent = applicationContext.resources.getIdentifier(
                                Constants.SHOW_SINGLE_COMPONENT,
                                Constants.DEF_TYPE_STRING,
                                applicationContext.packageName
                            )


                            if (showSingleComponent > 0 && getString(showSingleComponent).equals("1")) {

                                if (applicationContext.resources.getIdentifier(
                                        Constants.COMPONENT_NAME,
                                        Constants.DEF_TYPE_STRING,
                                        applicationContext.packageName
                                    ) > 0
                                ) {

                                    val componentName = getString(
                                        applicationContext.resources.getIdentifier(
                                            Constants.COMPONENT_NAME,
                                            Constants.DEF_TYPE_STRING,
                                            applicationContext.packageName
                                        )
                                    )

                                    val currentStatusArray =
                                        baseResponse.optJSONArray(Constants.CURRENT_STATUS)

                                    for (i in 0..currentStatusArray.length() - 1) {

                                        val currentStatus = currentStatusArray.optJSONObject(i)

                                        if (currentStatus.has(Constants.COMPONENT_GROUP_COMPONENTS)) {

                                            val componentGroupArray =
                                                currentStatus.optJSONArray(Constants.COMPONENT_GROUP_COMPONENTS)

                                            for (j in 0..componentGroupArray.length() - 1) {

                                                val componentGroup =
                                                    componentGroupArray.optJSONObject(j)

                                                if (componentGroup.optString(Constants.DISPLAY_NAME)
                                                        .equals(componentName)
                                                ) {
                                                    fetchSingleComponentDataFromServer(
                                                        service,
                                                        componentGroup.optString(Constants.ENCCOMPONENTID),
                                                        baseResponse.optJSONObject(Constants.STATUS_PAGE_DETAILS)
                                                            .optString(Constants.ENCSTATUSPAGEID),
                                                        componentGroup.optString(Constants.last_polled_time)
                                                    )
                                                } else {
                                                    if (j == componentGroupArray.length() - 1 && i == currentStatusArray.length() - 1 && !isStatusPageAvailable) {
                                                        progressBar.visibility = View.GONE
                                                        statusIQTv.visibility = View.VISIBLE
                                                        statusIQTv.setText(R.string.no_such_status_page_available)
                                                    }
                                                }
                                            }

                                        } else {
                                            if (currentStatus.optString(Constants.DISPLAY_NAME)
                                                    .equals(componentName)
                                            ) {
                                                fetchSingleComponentDataFromServer(
                                                    service,
                                                    currentStatus.optString(Constants.ENCCOMPONENTID),
                                                    baseResponse.optJSONObject(Constants.STATUS_PAGE_DETAILS)
                                                        .optString(Constants.ENCSTATUSPAGEID),
                                                    currentStatus.optString(Constants.last_polled_time)
                                                )
                                            } else {
                                                if (i == currentStatusArray.length() - 1 && !isStatusPageAvailable) {
                                                    progressBar.visibility = View.GONE
                                                    statusIQTv.visibility = View.VISIBLE
                                                    statusIQTv.setText(R.string.no_such_status_page_available)

                                                }
                                            }

                                        }
                                    }


                                } else {

                                    statusIQTv.visibility = View.VISIBLE
                                    statusIQTv.setText(R.string.provide_a_component_name)


                                }
                            } else {
                                progressBar.visibility = View.GONE

                                val fragment =
                                    supportFragmentManager.findFragmentById(R.id.fragment_container_view)

                                if (fragment is IncidentHistoryFragment) {

                                    val incidentHistoryFragment = IncidentHistoryFragment()
                                    val bundle = Bundle()
                                    bundle.putString(Constants.DATA, baseResponse.toString())
                                    incidentHistoryFragment.arguments = bundle


                                    supportFragmentManager.beginTransaction()
                                        .replace(R.id.fragment_container_view, incidentHistoryFragment)
                                        .commit()

                                } else {
                                    val statusIqFragment = StatusIqFragment()
                                    val bundle = Bundle()
                                    bundle.putString(Constants.DATA, baseResponse.toString())
                                    statusIqFragment.arguments = bundle

                                    supportFragmentManager.beginTransaction()
                                        .replace(R.id.fragment_container_view, statusIqFragment)
                                        .commit()

                                }

                            }
                        } else {
                            statusIQTv.visibility = View.VISIBLE
                            statusIQTv.setText(response.errorBody()?.string())
                        }

                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        statusIQTv.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        statusIQTv.setText(t.localizedMessage)
                    }
                })


            }
        }
        catch (e: Exception){

            progressBar.visibility=View.GONE
            statusIQTv.visibility=View.VISIBLE
            statusIQTv.setText(e.localizedMessage)

        }


    }

    fun fetchSingleComponentDataFromServer(
        service: RetrofitService,
        enc_component_id: String,
        enc_statuspage_id: String,
        lastPolledTime: String
    ) {

        isStatusPageAvailable = true

        val singleComponentResponse =
            service.getSingleComponentData("/sp/api/public/status_history/${enc_statuspage_id}?enc_component_ids=" + enc_component_id)

        singleComponentResponse.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE

                    singleComponentResponseBody =
                        JSONObject(response.body().toString()).optJSONObject(Constants.DATA)
                            .optJSONObject(enc_component_id)

                    val singleComponentFragment = SingleComponentFragment()

                    val bundle = Bundle()
                    bundle.putString(Constants.last_polled_time, lastPolledTime)
                    bundle.putString(Constants.DATA, singleComponentResponseBody?.toString())
                    singleComponentFragment.arguments = bundle

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, singleComponentFragment)
                        .commit()


                }
            }

            override fun onFailure(
                call: Call<String>,
                t: Throwable
            ) {
                progressBar.visibility = View.GONE
                statusIQTv.visibility = View.VISIBLE
                statusIQTv.setText(t.localizedMessage)

            }


        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId.equals(android.R.id.home)) {
            onBackPressed()
        }
        if (item.itemId.equals(R.id.incident_history)) {

            val incidentHistoryFragment = IncidentHistoryFragment()
            val bundle = Bundle()
            bundle.putString(Constants.DATA, baseResponse.toString())
            incidentHistoryFragment.arguments = bundle


            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, incidentHistoryFragment)
                .commit()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.status_iq_incident_history, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

        if (fragment is IncidentHistoryFragment) {

            val statusIqFragment = StatusIqFragment()
            val bundle = Bundle()
            bundle.putString(Constants.DATA, baseResponse.toString())
            statusIqFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, statusIqFragment)
                .commit()

            setActionBarTitle(actionBarTitle)
        } else {
            this.finish()
        }
    }


    fun setActionBarTitle(title: String) {
        supportActionBar?.setTitle(title)
    }

}
