package com.mkrdeveloper.foodappalpha

import android.content.Context
import com.mkrdeveloper.foodappalpha.listeners.RandomRecipeResponseListener
import com.mkrdeveloper.foodappalpha.models.RandomRecipeApiResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RequestManager(context: Context, tags : String) {


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
            api.getRandomRecipes(cont.getString(R.string.api_key), "100", tags )
                .awaitResponse()

            if (!response.isSuccessful) {
                listener.onError(response.message())

            }else{
            listener.onFetch(response.body(), response.message())
            }


                /*api.enqueue(object : Callback<RandomRecipeApiResponse> {
                override fun onResponse(
                    call: Call<RandomRecipeApiResponse>,
                    response: Response<RandomRecipeApiResponse>
                ) {
                    if (!response.isSuccessful) {
                        listener.onError(response.message())
                        return
                    }
                    listener.onFetch(response.body(), response.message())
                }

                override fun onFailure(call: Call<RandomRecipeApiResponse>, t: Throwable) {
                    t.message?.let { listener.onError(it) }
                }

            })

                 */

        }
    }
}



/* private interface CallRandomRecipes {
     @GET("recipes/random")

     fun getRandomRecipes(@Query("apiKey") apiKey : String, @Query("number") number : String, @Query("tags") tags : String): Call<RandomRecipeApiResponse>

 }

 */


