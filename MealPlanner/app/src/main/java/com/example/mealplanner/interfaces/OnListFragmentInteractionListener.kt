package com.example.mealplanner.interfaces

import com.example.mealplanner.dataClasses.Recipes

/**
 * This interface is used by the RecipeRecyclerViewAdapter to ensure
 * it has an appropriate Listener.
 *
 * In this app, it's implemented by RecipeFragmentFragment
 */
interface OnListFragmentInteractionListener {
    fun onItemClick(item: Recipes)
}