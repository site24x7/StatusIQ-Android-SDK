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


package com.zoho.statusiqsdk

import android.content.Context
import android.content.Intent


class StatusIq {


    companion object {

        internal var themeResId: Int? = null

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
            themeResId = themeStyleResId
        }

        @JvmStatic
        fun openStatusIqActivity(
            context: Context,
            actionBarTitle: String = "Status IQ",
            statusPageUrl: String
        ) {

            val intent = Intent(
                context,
                StatusIQActivity::class.java
            )

            intent.putExtra(Constants.ACTION_BAR_TITLE, actionBarTitle)

            intent.putExtra(Constants.STATUS_IQ_URL, statusPageUrl)

            context.startActivity(
                intent
            )
        }


        @JvmStatic
        fun openStatusIqActivity(
            context: Context,
            actionBarTitle: String = "Status IQ", statusPageUrl: String,showSingleComponent: Boolean,componentName: String
        ) {

            val intent = Intent(
                context,
                StatusIQActivity::class.java
            )

            intent.putExtra(Constants.ACTION_BAR_TITLE, actionBarTitle)
            intent.putExtra(Constants.STATUS_IQ_URL, statusPageUrl)
            intent.putExtra(Constants.SHOW_SINGLE_COMPONENT,showSingleComponent)
            intent.putExtra(Constants.COMPONENT_NAME,componentName)

            context.startActivity(
                intent
            )
        }



//        @JvmStatic
//        fun getStatusIqFragment(): Fragment {
//            return StatusIqFragment()
//        }

    }

}