package com.example.mealplanner


import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mealplanner.adapters.FavoriteAdapter
import com.example.mealplanner.dataClasses.RecipeData
import com.example.mealplanner.dataClasses.RecipeEntity
import com.example.mealplanner.database.RecipeApplication
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class AddingActivity : AppCompatActivity() {
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var imageView: ImageView
    private lateinit var selectedImageUri: Uri

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { galleryUri ->
        try {
            galleryUri?.let { uri ->
                selectedImageUri = uri
                imageView.setImageURI(uri)

            } ?: run {
                Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_recipe)

        val recipeName = findViewById<EditText>(R.id.recipeNameX)
        val instruction = findViewById<EditText>(R.id.recipeInstructionX)
        val ingredient1 = findViewById<EditText>(R.id.ingredient1X)
        val ingredient2 = findViewById<EditText>(R.id.ingredient2X)
        val ingredient3 = findViewById<EditText>(R.id.ingredient3X)
        val ingredient4 = findViewById<EditText>(R.id.ingredient4X)
        val ingredient5 = findViewById<EditText>(R.id.ingredient5X)
        val measure1 = findViewById<EditText>(R.id.measure1X)
        val measure2 = findViewById<EditText>(R.id.measure2X)
        val measure3 = findViewById<EditText>(R.id.measure3X)
        val measure4 = findViewById<EditText>(R.id.measure4X)
        val measure5 = findViewById<EditText>(R.id.measure5X)
        val addButton = findViewById<Button>(R.id.addingRecipeX)
        val addImage = findViewById<Button>(R.id.addImage)
        imageView = findViewById(R.id.image)


        addImage.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        addButton.setOnClickListener {
            lifecycleScope.launch(IO) {
                (application as RecipeApplication).db.recipeDao().insert(
                    RecipeEntity(
                        name = recipeName.text.toString(),
                        imageLink = "https://www.themealdb.com/images/media/meals/urtwux1486983078.jpg",
                        instructions = instruction.text.toString(),
                        ingredient1 = ingredient1.text.toString(),
                        ingredient2 = ingredient2.text.toString(),
                        ingredient3 = ingredient3.text.toString(),
                        ingredient4 = ingredient4.text.toString(),
                        ingredient5 = ingredient5.text.toString(),
                        measure1 = measure1.text.toString(),
                        measure2 = measure2.text.toString(),
                        measure3 = measure3.text.toString(),
                        measure4 = measure4.text.toString(),
                        measure5 = measure5.text.toString(),
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
            Toast.makeText(this, "Recipe Added", Toast.LENGTH_SHORT).show()
            recipeName.text.clear()
            instruction.text.clear()
            ingredient1.text.clear()
            ingredient2.text.clear()
            ingredient3.text.clear()
            ingredient4.text.clear()
            ingredient5.text.clear()
            measure1.text.clear()
            measure2.text.clear()
            measure3.text.clear()
            measure4.text.clear()
            measure5.text.clear()

        }


    }

}