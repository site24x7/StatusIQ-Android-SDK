package com.zoho.statusiqsdk

import android.content.Context
import android.content.Intent


class StatusIq {


    companion object {

        internal var themeResId:Int ?=null

        @JvmStatic
        fun openStatusIqActivity(
            context: Context,
            actionBarTitle: String = "Status IQ"
        ) {

            val intent = Intent(
                context,
                StatusIQActivity::class.java
            )

            intent.putExtra(Constants.ACTION_BAR_TITLE, actionBarTitle)

            context.startActivity(
                intent
            )
        }

        @JvmStatic
        fun setTheme(themeStyleResId: Int) {
            themeResId=themeStyleResId
        }


//        @JvmStatic
//        fun getStatusIqFragment(): Fragment {
//            return StatusIqFragment()
//        }

    }

}