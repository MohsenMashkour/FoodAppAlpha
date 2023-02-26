package com.mkrdeveloper.foodappalpha.listeners

import com.mkrdeveloper.foodappalpha.models.RandomRecipeApiResponse

interface RandomRecipeResponseListener {


        suspend fun onFetch(response: RandomRecipeApiResponse?, message: String)


        suspend fun onError(msg: String)



}