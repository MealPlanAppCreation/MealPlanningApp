package com.example.mealplanner.database

import android.app.Application

class RecipeApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}