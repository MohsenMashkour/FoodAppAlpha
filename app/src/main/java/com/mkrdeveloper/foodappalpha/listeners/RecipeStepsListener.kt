package com.mkrdeveloper.foodappalpha.listeners

import com.mkrdeveloper.foodappalpha.models.recipesSteps.RecipesSteps

interface RecipeStepsListener {


        suspend fun onFetch(response: RecipesSteps?, message: String)


        suspend fun onError(msg: String)



}