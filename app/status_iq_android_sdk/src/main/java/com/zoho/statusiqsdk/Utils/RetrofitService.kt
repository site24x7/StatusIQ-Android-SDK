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

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

internal interface RetrofitService {

    @GET("/sp/api/u/summary_details")
     fun getStatusPageData(): Call<String>

    @GET()
     fun getSingleComponentData(@Url url:String): Call<String>
}


internal object RetrofitFactory {

    fun makeRetrofitService(baseUrl:String): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }


}