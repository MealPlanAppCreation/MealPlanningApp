package com.example.mealplanner.dataClasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipeName") val name: String?,
    @ColumnInfo(name = "imageLink") val imageLink: String?,
    @ColumnInfo(name = "instructions") val instructions: String?,
    @ColumnInfo(name = "ingredient1") val ingredient1: String?,
    @ColumnInfo(name = "ingredient2") val ingredient2: String?,
    @ColumnInfo(name = "ingredient3") val ingredient3: String?,
    @ColumnInfo(name = "ingredient4") val ingredient4: String?,
    @ColumnInfo(name = "ingredient5") val ingredient5: String?,
    @ColumnInfo(name = "measure1") val measure1: String?,
    @ColumnInfo(name = "measure2") val measure2: String?,
    @ColumnInfo(name = "measure3") val measure3: String?,
    @ColumnInfo(name = "measure4") val measure4: String?,
    @ColumnInfo(name = "measure5") val measure5: String?,
)