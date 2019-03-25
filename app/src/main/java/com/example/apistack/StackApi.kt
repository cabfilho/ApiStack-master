package com.example.apistack

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface StackApi {
    @GET("questions")
    fun getQuestions(@Query("order") order: String, @Query("sort") sort: String,
                     @Query("tagged") tagged: String,@Query("site") site: String): Call<StackQuestion>

    //https://api.stackexchange.com/2.2/questions?order=desc%20&sort=activity&tagged=android&site=stackoverflow




}