package com.example.mealplanner.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanner.DetailActivity
import com.example.mealplanner.adapters.FavoriteAdapter
import com.example.mealplanner.R
import com.example.mealplanner.dataClasses.RecipeData
import com.example.mealplanner.dataClasses.Recipes
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FavoriteListFragment  : Fragment() {
    private val recipes = mutableListOf<RecipeData>()
    private lateinit var recipesRecyclerView: RecyclerView
    private lateinit var recipesAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        recipesRecyclerView = view.findViewById(R.id.recipe_recycler_view)
        recipesRecyclerView.layoutManager = layoutManager
        recipesRecyclerView.setHasFixedSize(true)

        val searchButton = requireActivity().findViewById<Button>(R.id.searchButton)
        val searchEditText = requireActivity().findViewById<TextInputEditText>(R.id.textInputEditText)
        val textInputLayout = requireActivity().findViewById<TextInputLayout>(R.id.textInputLayout)
        val calendarView = requireActivity().findViewById<com.applandeo.materialcalendarview.CalendarView>(R.id.calendar)
        textInputLayout.visibility = View.GONE
        searchEditText.visibility = View.GONE
        calendarView.visibility = View.VISIBLE
        searchButton.visibility = View.GONE
        //change
        recipesAdapter = FavoriteAdapter(recipes) { recipe ->
            onRecipeClick(recipe)
        }
        recipesRecyclerView.adapter = recipesAdapter

        return view
    }

    fun updateData(newFoodList: List<RecipeData>) {
        if (::recipesAdapter.isInitialized) {
            recipesAdapter.updateFoods(newFoodList)
        } else {
            recipes.clear()
            recipes.addAll(newFoodList)
        }
    }

    // Function to handle item clicks
    private fun onRecipeClick(recipe: RecipeData) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra("EXTRA_NAME", recipe.name)
            putExtra("EXTRA_IMAGE", recipe.imageLink)
            putExtra("EXTRA_INSTRUCTIONS", recipe.instructions)
            putExtra("EXTRA_INGREDIENT_1", recipe.ingredient1)
            putExtra("EXTRA_INGREDIENT_2", recipe.ingredient2)
            putExtra("EXTRA_INGREDIENT_3", recipe.ingredient3)
            putExtra("EXTRA_INGREDIENT_4", recipe.ingredient4)
            putExtra("EXTRA_INGREDIENT_5", recipe.ingredient5)
            putExtra("EXTRA_MEASURE_1", recipe.measure1)
            putExtra("EXTRA_MEASURE_2", recipe.measure2)
            putExtra("EXTRA_MEASURE_3", recipe.measure3)
            putExtra("EXTRA_MEASURE_4", recipe.measure4)
            putExtra("EXTRA_MEASURE_5", recipe.measure5)
        }
        context?.startActivity(intent)
    }

    companion object {
        fun newInstance(): FavoriteListFragment {
            return FavoriteListFragment()
        }
    }
}