package com.example.mealplanner.dataClasses

data class RecipeData(
    val id: Int,
    val name: String?,
    val imageLink: String?,
    val instructions: String?,
    val ingredient1: String?,
    val ingredient2: String?,
    val ingredient3: String?,
    val ingredient4: String?,
    val ingredient5: String?,
    val measure1: String?,
    val measure2: String?,
    val measure3: String?,
    val measure4: String?,
    val measure5: String?,
) : java.io.Serializable