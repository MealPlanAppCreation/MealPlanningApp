package com.example.mealplanner.fragments

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.mealplanner.DetailActivity
import com.example.mealplanner.interfaces.OnListFragmentInteractionListener
import com.example.mealplanner.R
import com.example.mealplanner.dataClasses.Recipes
import com.example.mealplanner.adapters.RecipesRecyclerViewAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

// Free-source API Key
private const val recipeURL = "https://www.themealdb.com/api/json/v1/1/search.php?s="
private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
private const val SEARCH_BY_NAME = "search.php?s="
private const val SEARCH_BY_FIRST_LETTER = "search.php?f="

class RecipesFragment : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        val searchEditText = requireActivity().findViewById<TextInputEditText>(R.id.textInputEditText)
        val searchButton = requireActivity().findViewById<Button>(R.id.searchButton)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(recipeURL,progressBar, recyclerView)
        val calendarView = requireActivity().findViewById<com.applandeo.materialcalendarview.CalendarView>(R.id.calendar)
        val textInputLayout = requireActivity().findViewById<TextInputLayout>(R.id.textInputLayout)
        textInputLayout.visibility = View.VISIBLE
        searchEditText.visibility = View.VISIBLE
        calendarView.visibility = View.VISIBLE
        searchButton.visibility = View.VISIBLE
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            val apiUrl = if (query.length == 1) {
                "https://www.themealdb.com/api/json/v1/1/search.php?f=$query"
            } else if (query.isEmpty()) {
                recipeURL
            }
            else {
                "https://www.themealdb.com/api/json/v1/1/search.php?s=$query"
            }
            updateAdapter(apiUrl, progressBar, recyclerView)
        }

        return view
    }

    private fun updateAdapter(api: String, progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val client = AsyncHttpClient()
        client[api, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JSON
            ) {
                progressBar.hide()

                // Parse JSON response
                val mealsJSON = json.jsonObject.getJSONArray("meals")
                val gson = Gson()
                val arrayRecipeType = object : TypeToken<List<Recipes>>() {}.type
                val recipes: List<Recipes> = gson.fromJson(mealsJSON.toString(), arrayRecipeType)

                // Update RecyclerView adapter
                recyclerView.adapter = RecipesRecyclerViewAdapter(recipes, this@RecipesFragment)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
            }
        }]

    }

    override fun onItemClick(item: Recipes) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("EXTRA_NAME", item.recipeName)
        intent.putExtra("EXTRA_IMAGE", item.recipeImage)
        intent.putExtra("EXTRA_INSTRUCTIONS", item.recipeInstruction)
        intent.putExtra("EXTRA_INGREDIENT_1", item.ingredient1)
        intent.putExtra("EXTRA_INGREDIENT_2", item.ingredient2)
        intent.putExtra("EXTRA_INGREDIENT_3", item.ingredient3)
        intent.putExtra("EXTRA_INGREDIENT_4", item.ingredient4)
        intent.putExtra("EXTRA_INGREDIENT_5", item.ingredient5)
        intent.putExtra("EXTRA_MEASURE_1", item.measure1)
        intent.putExtra("EXTRA_MEASURE_2", item.measure2)
        intent.putExtra("EXTRA_MEASURE_3", item.measure3)
        intent.putExtra("EXTRA_MEASURE_4", item.measure4)
        intent.putExtra("EXTRA_MEASURE_5", item.measure5)
        context?.startActivity(intent)
    }


}