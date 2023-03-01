package com.mkrdeveloper.foodappalpha.listeners

import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesResponse

interface SimilarRecipeResponseListener {


        suspend fun onFetch(response: SimilarRecipesResponse?, message: String)


        fun onError(msg: String)



}