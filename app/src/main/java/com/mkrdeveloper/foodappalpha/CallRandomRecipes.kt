package com.mkrdeveloper.foodappalpha

import com.mkrdeveloper.foodappalpha.models.DetailsOfRecipes
import com.mkrdeveloper.foodappalpha.models.RandomRecipeApiResponse
import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CallRandomRecipes {

    @GET("recipes/random")

    fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: String,
        @Query("tags") tags: String
    ): Call<RandomRecipeApiResponse>

    //fun enqueue(param: Callback<RandomRecipeApiResponse>, function: () -> Unit)
    fun enqueue(param: Callback<RandomRecipeApiResponse>)

    @GET("recipes/{id}/information")

    fun getDetailOfRecipes(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,

        ): Call<DetailsOfRecipes>


    @GET("recipes/{id}/similar")

    fun getSimilarRecipes(
        @Path("id") id: Int,
        @Query("number") number: String,
        @Query("apiKey") apiKey: String,

        ): Call<SimilarRecipesResponse>

}