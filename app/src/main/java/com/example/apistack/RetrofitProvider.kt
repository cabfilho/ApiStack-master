package com.example.apistack

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

      // https://api.stackexchange.com/2.2/questions?order=desc%20&sort=activity&tagged=android&site=stackoverflow
        private val retrofitInstance =
            Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com/2.2/")

                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val stackApi:StackApi = retrofitInstance.create(StackApi::class.java)



}
