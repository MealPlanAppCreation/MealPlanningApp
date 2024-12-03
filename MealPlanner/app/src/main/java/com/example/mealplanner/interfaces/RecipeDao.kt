package com.example.mealplanner.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mealplanner.dataClasses.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe_table")
    fun getAll(): Flow<List<RecipeEntity>>

    @Insert
    fun insert(entryEntity: RecipeEntity)

    @Delete
    fun delete(entryEntity: RecipeEntity)

    @Insert
    fun insertAll(entryList: List<RecipeEntity>)

    @Query("DELETE FROM recipe_table")
    fun deleteAll()

    @Query("SELECT * FROM recipe_table WHERE recipeName = :name LIMIT 1")
    fun getRecipeByName(name: String?): RecipeEntity?
}