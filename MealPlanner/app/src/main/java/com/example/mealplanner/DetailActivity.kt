package com.example.mealplanner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.mealplanner.adapters.FavoriteAdapter
import com.example.mealplanner.dataClasses.RecipeData
import com.example.mealplanner.dataClasses.RecipeEntity
import com.example.mealplanner.database.RecipeApplication
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class DetailActivity : AppCompatActivity() {
    private lateinit var recipeImageView: ImageView
    private lateinit var recipeNameView: TextView
    private lateinit var recipeInstructionView: TextView
    private lateinit var addFavoriteButton: Button
    private lateinit var addCalendarButton: Button
    private lateinit var removeFavoriteButton: Button
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var recipeIngredient1: TextView
    private lateinit var recipeIngredient2: TextView
    private lateinit var recipeIngredient3: TextView
    private lateinit var recipeIngredient4: TextView
    private lateinit var recipeIngredient5: TextView
    private lateinit var recipeMeasure1: TextView
    private lateinit var recipeMeasure2: TextView
    private lateinit var recipeMeasure3: TextView
    private lateinit var recipeMeasure4: TextView
    private lateinit var recipeMeasure5: TextView
    private lateinit var calendarDate: TextInputEditText


    @SuppressLint("MissingInflatedId", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        recipeImageView = findViewById(R.id.mediaRecipeImage)
        recipeNameView = findViewById(R.id.mediaRecipeName)
        recipeInstructionView= findViewById(R.id.mediaRecipeInstruction)
        addFavoriteButton = findViewById(R.id.addFavorite)
        addCalendarButton = findViewById(R.id.addCalendar)
        removeFavoriteButton = findViewById(R.id.removeFavorite)
        recipeIngredient1 = findViewById(R.id.ingredient1)
        recipeIngredient2 = findViewById(R.id.ingredient2)
        recipeIngredient3= findViewById(R.id.ingredient3)
        recipeIngredient4 = findViewById(R.id.ingredient4)
        recipeIngredient5 = findViewById(R.id.ingredient5)
        recipeMeasure1= findViewById(R.id.measure1)
        recipeMeasure2= findViewById(R.id.measure2)
        recipeMeasure3 = findViewById(R.id.measure3)
        recipeMeasure4 = findViewById(R.id.measure4)
        recipeMeasure5 = findViewById(R.id.measure5)
        calendarDate = findViewById(R.id.calendarDateID)

        val name = intent.getStringExtra("EXTRA_NAME")
        val instruction = intent.getStringExtra("EXTRA_INSTRUCTIONS")
        val image = intent.getStringExtra("EXTRA_IMAGE")
        val ingredient1 = intent.getStringExtra("EXTRA_INGREDIENT_1")
        val ingredient2 = intent.getStringExtra("EXTRA_INGREDIENT_2")
        val ingredient3 = intent.getStringExtra("EXTRA_INGREDIENT_3")
        val ingredient4 = intent.getStringExtra("EXTRA_INGREDIENT_4")
        val ingredient5 = intent.getStringExtra("EXTRA_INGREDIENT_5")

        val measure1 = intent.getStringExtra("EXTRA_MEASURE_1")
        val measure2 = intent.getStringExtra("EXTRA_MEASURE_2")
        val measure3 = intent.getStringExtra("EXTRA_MEASURE_3")
        val measure4 = intent.getStringExtra("EXTRA_MEASURE_4")
        val measure5 = intent.getStringExtra("EXTRA_MEASURE_5")


        recipeNameView.text = name
        recipeInstructionView.text = instruction
        Glide.with(this)
            .load(image)
            .into(recipeImageView)
        recipeIngredient1.text = ingredient1
        recipeIngredient2.text = ingredient2
        recipeIngredient3.text = ingredient3
        recipeIngredient4.text = ingredient4
        recipeIngredient5.text = ingredient5
        recipeMeasure1.text = measure1
        recipeMeasure2.text = measure2
        recipeMeasure3.text = measure3
        recipeMeasure4.text = measure4
        recipeMeasure5.text = measure5

        addFavoriteButton.visibility = View.GONE
        removeFavoriteButton.visibility = View.GONE
        lifecycleScope.launch(IO) {
            val isRecipeInDatabase = (application as RecipeApplication).db.recipeDao().getRecipeByName(name) != null

            withContext(Dispatchers.Main) {
                addFavoriteButton.visibility = if (!isRecipeInDatabase) View.VISIBLE else View.GONE
                removeFavoriteButton.visibility = if (isRecipeInDatabase) View.VISIBLE else View.GONE
            }
        }

        removeFavoriteButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                val recipeToDelete = (application as RecipeApplication).db.recipeDao().getRecipeByName(name)
                if (recipeToDelete != null) {
                    (application as RecipeApplication).db.recipeDao().delete(recipeToDelete)
                }
                val newFoods = (application as RecipeApplication).db.recipeDao().getAll().first()
                val mappedFoods = newFoods.map { recipeEntity ->
                    RecipeData(
                        id = recipeEntity.id,
                        name = recipeEntity.name,
                        imageLink = recipeEntity.imageLink,
                        instructions =  recipeEntity.instructions,
                        ingredient1 = recipeEntity.ingredient1,
                        ingredient2 = recipeEntity.ingredient2,
                        ingredient3 = recipeEntity.ingredient3,
                        ingredient4 = recipeEntity.ingredient4,
                        ingredient5 = recipeEntity.ingredient5,
                        measure1 = recipeEntity.measure1,
                        measure2 = recipeEntity.measure2,
                        measure3 = recipeEntity.measure3,
                        measure4 = recipeEntity.measure4,
                        measure5 = recipeEntity.measure5,
                    )
                }
                favoriteAdapter.updateFoods(mappedFoods)
            }
            Toast.makeText(this, "Recipe Remove From Favorites", Toast.LENGTH_SHORT).show()
        }

        addCalendarButton.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val date = calendarDate.text.toString()
            val calendarRecipe = hashMapOf(
                "date" to date,
                "name" to name,
                "imageLink" to image,
                "instructions" to instruction,
                "ingredient1" to ingredient1,
                "ingredient2" to ingredient2,
                "ingredient3" to ingredient3,
                "ingredient4" to ingredient4,
                "ingredient5" to ingredient5,
                "measure1" to measure1,
                "measure2" to measure2,
                "measure3" to measure3,
                "measure4" to measure4,
                "measure5" to measure5,
            )
            db.collection("calendar")
                .document(calendarRecipe["date"] as String)
                .set(calendarRecipe)
                .addOnSuccessListener {
                    Toast.makeText(this, "Recipe added to calendar!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        addFavoriteButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as RecipeApplication).db.recipeDao().insert(
                    RecipeEntity(
                        name = name,
                        imageLink = image,
                        instructions = instruction,
                        ingredient1 = ingredient1,
                        ingredient2 = ingredient2,
                        ingredient3 = ingredient3,
                        ingredient4 = ingredient4,
                        ingredient5 = ingredient5,
                        measure1 = measure1,
                        measure2 = measure2,
                        measure3 = measure3,
                        measure4 = measure4,
                        measure5 = measure5,
                    )
                )
                val newFoods = (application as RecipeApplication).db.recipeDao().getAll().first()
                val mappedFoods = newFoods.map { recipeEntity ->
                    RecipeData(
                        id = recipeEntity.id,
                        name = recipeEntity.name,
                        imageLink = recipeEntity.imageLink,
                        instructions =  recipeEntity.instructions,
                        ingredient1 = recipeEntity.ingredient1,
                        ingredient2 = recipeEntity.ingredient2,
                        ingredient3 = recipeEntity.ingredient3,
                        ingredient4 = recipeEntity.ingredient4,
                        ingredient5 = recipeEntity.ingredient5,
                        measure1 = recipeEntity.measure1,
                        measure2 = recipeEntity.measure2,
                        measure3 = recipeEntity.measure3,
                        measure4 = recipeEntity.measure4,
                        measure5 = recipeEntity.measure5,
                    )
                }
                favoriteAdapter.updateFoods(mappedFoods)
            }
            Toast.makeText(this, "Recipe Added To Favorites", Toast.LENGTH_SHORT).show()
        }


    }
}