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

package com.zoho.statusiqsdkapp


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoho.statusiqsdk.StatusIq


class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var navigationView: NavigationView
    lateinit var drawer: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var nameRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        nameRecyclerView = findViewById(R.id.nameRecyclerView)

        toolbar.title = "Zylker"
        setSupportActionBar(toolbar)


        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        nameRecyclerView.layoutManager = LinearLayoutManager(this)
        nameRecyclerView.adapter = NameRecyclerAdapter(generateList(), this)


        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        ) {

        }


        actionBarDrawerToggle.syncState()

    }


    fun generateList(): ArrayList<String> {

        val nameList = ArrayList<String>()


        nameList.add("Cloud Computing")
        nameList.add("Public Cloud")
        nameList.add("Private Cloud")
        nameList.add("Hybrid Cloud")
        nameList.add("Multi Cloud")
        nameList.add("Service Status")

        return nameList
    }


    override fun onClick(position: Int, name: String) {

        when (position) {

            0 -> {
                drawer.closeDrawers()
                startActivity(Intent(this@MainActivity, ActivityCloudComputing::class.java))

            }

            1 -> {
                drawer.closeDrawers()
                startActivity(Intent(this@MainActivity, ActivityPublicCloud::class.java))


            }

            2 -> {
                drawer.closeDrawers()
                startActivity(Intent(this@MainActivity, ActivityPrivateCloud::class.java))


            }

            3 -> {
                drawer.closeDrawers()
                startActivity(Intent(this@MainActivity, ActivityHybridCloud::class.java))


            }

            4 -> {
                drawer.closeDrawers()
                startActivity(Intent(this@MainActivity, ActivityMultiCloud::class.java))


            }

            5 -> {

                drawer.closeDrawers()

                StatusIq.setTheme(R.style.Theme_StatusIQSDK)
                StatusIq.openStatusIqActivity(this@MainActivity, "Service status")
                StatusIq.setColors(R.color.black, R.color.white)
//                StatusIq.openStatusIqActivity(this@MainActivity, "Service status","https://status.site24x7.com",true,"Site24x7 StatusIQ")


            }


        }


    }

}

interface OnClickListener {

    fun onClick(position: Int, name: String)

}