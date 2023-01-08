package com.mkrdeveloper.foodappalpha

import com.mkrdeveloper.foodappalpha.models.RandomRecipeApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface CallRandomRecipes {

    @GET("recipes/random")

    fun getRandomRecipes(@Query("apiKey") apiKey : String, @Query("number") number : String, @Query("tags") tags : String): Call<RandomRecipeApiResponse>
    //fun enqueue(param: Callback<RandomRecipeApiResponse>, function: () -> Unit)
    fun enqueue(param: Callback<RandomRecipeApiResponse>)


}