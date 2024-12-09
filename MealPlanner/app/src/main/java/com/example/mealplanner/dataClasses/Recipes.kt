package com.example.mealplanner.dataClasses

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from MovieDB
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Recipes {
    @JvmField
    @SerializedName("strMeal")
    var recipeName: String? = null

    @SerializedName("strMealThumb")
    var recipeImage: String? = null

    @SerializedName("strInstructions")
    var recipeInstruction: String? = null

    @SerializedName("strIngredient1")
    var ingredient1: String? = null

    @SerializedName("strIngredient2")
    var ingredient2: String? = null

    @SerializedName("strIngredient3")
    var ingredient3: String? = null

    @SerializedName("strIngredient4")
    var ingredient4: String? = null

    @SerializedName("strIngredient5")
    var ingredient5: String? = null

    @SerializedName("strMeasure1")
    var measure1: String? = null

    @SerializedName("strMeasure2")
    var measure2: String? = null

    @SerializedName("strMeasure3")
    var measure3: String? = null

    @SerializedName("strMeasure4")
    var measure4: String? = null

    @SerializedName("strMeasure5")
    var measure5: String? = null


}