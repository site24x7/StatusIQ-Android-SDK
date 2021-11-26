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