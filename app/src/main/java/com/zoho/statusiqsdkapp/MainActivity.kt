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


//        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.frame, StatusIq.getStatusIqFragment())
//        transaction.commit()

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
//                        StatusIq.setTheme(R.style.Theme_StatusIQSDK)
                StatusIq.openStatusIqActivity(this@MainActivity, "Service status")
            }


        }


    }

}

interface OnClickListener {

    fun onClick(position: Int, name: String)

}