package com.mkrdeveloper.foodappalpha.listeners

import com.mkrdeveloper.foodappalpha.models.DetailsOfRecipes

interface DetailsRecipeResponseListener {


        suspend fun onFetch(response: DetailsOfRecipes?, message: String)


        fun onError(msg: String)



}