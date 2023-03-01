package com.mkrdeveloper.foodappalpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mkrdeveloper.foodappalpha.adapters.RvCatAdapter
import com.mkrdeveloper.foodappalpha.adapters.recAdapter
import com.mkrdeveloper.foodappalpha.listeners.CategoryListener
import com.mkrdeveloper.foodappalpha.listeners.RandomRecipeResponseListener
import com.mkrdeveloper.foodappalpha.models.RandomRecipeApiResponse
import com.mkrdeveloper.foodappalpha.models.Recipes
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

const val BASE_URL = "https://api.spoonacular.com/"

class MainActivity : AppCompatActivity() {

    private val TAG = "my tag"

    private lateinit var recView: RecyclerView
    private lateinit var itemArrayList: ArrayList<Recipes>
    private lateinit var listener: RandomRecipeResponseListener

    private var tags: String = "main course"

    private lateinit var tvMain: TextView
    private lateinit var tvLabel: TextView

    private lateinit var searchView: SearchView
    private lateinit var recAdapter: recAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView = findViewById(R.id.recyclerView)
        recView.layoutManager = GridLayoutManager(this, 1)
        recView.setHasFixedSize(true)

        itemArrayList = arrayListOf()
        categorySet()


        setSupportActionBar(findViewById(R.id.toolbar))



        tvMain = findViewById(R.id.tvMain)
        tvLabel = findViewById(R.id.tv_label)


        recAdapter = recAdapter(itemArrayList)

        searchView = findViewById(R.id.searchView)
        searchView.clearFocus()

        onSearchApplied()


        // getData()

        listener = object : RandomRecipeResponseListener {

            override suspend fun onFetch(response: RandomRecipeApiResponse?, message: String) {


                if (response != null) {
                    itemArrayList = response.recipes as ArrayList<Recipes>
                }

                Log.d(TAG, itemArrayList.toString())



                withContext(Dispatchers.Main) {

                    recAdapter = recAdapter(itemArrayList)
                    recView.adapter = recAdapter
                }

            }

            override suspend fun onError(msg: String) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "error $msg", Toast.LENGTH_SHORT).show()
                }
            }


        }
        RequestManager(applicationContext, tags, 0).getRandomRecipes(listener)
    }

    private fun categorySet() {
        val catList = arrayListOf(
            "main course",
            "side dish",
            "appetizer",
            "salad",
            "bread",
            "breakfast",
            "soup",
            "beverage",
            "sauce",
            "marinade",
            "dessert",
            "fingerfood",
            "snack",
            "drink"
        )

        val categoryListener = object : CategoryListener{
            override fun categoryTag(tag: Int) {
                tags = catList[tag]
                Toast.makeText(this@MainActivity,catList[tag],Toast.LENGTH_SHORT).show()
                RequestManager(applicationContext, catList[tag], 0).getRandomRecipes(listener)
                tvLabel.text = tags
            }
        }

        val rvCat = findViewById<RecyclerView>(R.id.rvCategory)
        rvCat.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MainActivity,1,RecyclerView.HORIZONTAL,false)
            val adaptr = RvCatAdapter(categoryListener)
            adapter = adaptr
        }


    }

    private fun onSearchApplied() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                if (query != null) {
                    tags = query
                }
                RequestManager(applicationContext, tags, 0).getRandomRecipes(listener)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {


                val searchList = ArrayList<Recipes>()

                if (newText != null) {
                    for (i in itemArrayList) {
                        if (i.title.lowercase(Locale.ROOT).contains(newText)) {
                            searchList.add(i)
                        }
                    }
                    if (searchList.isEmpty()) {
                        Toast.makeText(this@MainActivity, "No data", Toast.LENGTH_SHORT).show()
                    } else {

                        recAdapter.onSearchApplied(searchList)
                    }
                }
                return true
            }

        })
    }

    /*@OptIn(DelicateCoroutinesApi::class)
    private fun getData() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CallRandomRecipes::class.java)

        GlobalScope.launch(Dispatchers.IO) {


            val response: Response<RandomRecipeApiResponse> =
                api.getRandomRecipes(applicationContext.getString(R.string.api_key), "100", tags)
                    .awaitResponse()



            Log.d(TAG, response.toString())
            if (response.isSuccessful) {
                val data = response.body()!!



                itemArrayList = data.recipes as ArrayList<Recipes>

                Log.d(TAG, itemArrayList.toString())

                withContext(Dispatchers.Main) {
                    //tvMain.text = itemArrayList.toString()

                    recView.adapter = recAdapter(itemArrayList)


                }


            }
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.categories_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val cont = applicationContext
        when (item.itemId) {
            R.id.main_course -> tags = cont.getString(R.string.main_course)
            R.id.side_dish -> tags = cont.getString(R.string.side_dish)
            R.id.dessert -> tags = cont.getString(R.string.dessert)
            R.id.appetizer -> tags = cont.getString(R.string.appetizer)
            R.id.salad -> tags = cont.getString(R.string.salad)
            R.id.bread -> tags = cont.getString(R.string.bread)
            R.id.breakfast -> tags = cont.getString(R.string.breakfast)
            R.id.soup -> tags = cont.getString(R.string.soup)
            R.id.beverage -> tags = cont.getString(R.string.beverage)
            R.id.sauce -> tags = cont.getString(R.string.sauce)
            R.id.marinade -> tags = cont.getString(R.string.marinade)
            R.id.finger_food -> tags = cont.getString(R.string.finger_food)
            R.id.snack -> tags = cont.getString(R.string.snack)
            R.id.drink -> tags = cont.getString(R.string.drink)

        }
        tvLabel.text = tags
        RequestManager(cont, tags, 0).getRandomRecipes(listener)


        return super.onOptionsItemSelected(item)
    }
}