package com.mkrdeveloper.foodappalpha

import android.content.Context
import com.mkrdeveloper.foodappalpha.listeners.DetailsRecipeResponseListener
import com.mkrdeveloper.foodappalpha.listeners.RandomRecipeResponseListener
import com.mkrdeveloper.foodappalpha.listeners.SimilarRecipeResponseListener
import com.mkrdeveloper.foodappalpha.models.DetailsOfRecipes
import com.mkrdeveloper.foodappalpha.models.RandomRecipeApiResponse
import com.mkrdeveloper.foodappalpha.models.similarRecipes.SimilarRecipesResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RequestManager(context: Context, tags: String, id : Int) {


    val id = id
    val tags = tags
    val cont = context
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CallRandomRecipes::class.java)


    @OptIn(DelicateCoroutinesApi::class)
    fun getRandomRecipes(listener: RandomRecipeResponseListener) {


        GlobalScope.launch(Dispatchers.IO) {


            val response: Response<RandomRecipeApiResponse> =
                api.getRandomRecipes(cont.getString(R.string.api_key), "100", tags)
                    .awaitResponse()

            if (!response.isSuccessful) {
                listener.onError(response.message())

            } else {
                listener.onFetch(response.body(), response.message())
            }


        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getDetailRecipes(listener: DetailsRecipeResponseListener) {


        GlobalScope.launch(Dispatchers.IO) {


            val response: Response<DetailsOfRecipes> =
                api.getDetailOfRecipes(id,cont.getString(R.string.api_key))
                    .awaitResponse()

            if (!response.isSuccessful) {
                listener.onError(response.message())

            } else {
                listener.onFetch(response.body(), response.message())
            }


        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getSimilarRecipes(listener: SimilarRecipeResponseListener) {


        GlobalScope.launch(Dispatchers.IO) {


            val response: Response<SimilarRecipesResponse> =
                api.getSimilarRecipes(id,"3",cont.getString(R.string.api_key))
                    .awaitResponse()

            if (!response.isSuccessful) {
                listener.onError(response.message())

            } else {
                listener.onFetch(response.body(), response.message())
            }


        }
    }
}






