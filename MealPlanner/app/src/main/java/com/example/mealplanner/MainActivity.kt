package com.example.mealplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.example.mealplanner.dataClasses.RecipeData
import com.example.mealplanner.dataClasses.Recipes
import com.example.mealplanner.database.RecipeApplication
import com.example.mealplanner.databinding.ActivityMainBinding
import com.example.mealplanner.fragments.FavoriteListFragment
import com.example.mealplanner.fragments.RecipesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView
    private var events: MutableMap<String, String> = mutableMapOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var recipesFragment: RecipesFragment
    private lateinit var favoriteFragment: FavoriteListFragment
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


        recipesFragment = RecipesFragment()
        favoriteFragment = FavoriteListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_home -> fragment = recipesFragment
                R.id.nav_favorite -> fragment = favoriteFragment
            }
            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.nav_home
        loadDatabase()

        val db = FirebaseFirestore.getInstance()
        calendarView = findViewById(R.id.calendar)
        val events = mutableMapOf<String, String>()
        val calendars: ArrayList<CalendarDay> = ArrayList()

        fun loadEvents() {
            db.collection("calendar")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val date = document.getString("date") ?: continue
                        val recipeName = document.getString("name") ?: "Recipe"

                        // Add to events map
                        events[date] = recipeName

                        // Parse the date into a Calendar object
                        val parts = date.split("-") // Assuming "YYYY-MM-DD" format
                        if (parts.size == 3) {
                            val year = parts[2].toInt()
                            val month = parts[1].toInt() - 1 // Calendar months are 0-based
                            val day = parts[0].toInt()

                            val calendar = Calendar.getInstance()
                            calendar.set(year, month, day)

                            // Create a CalendarDay object and style it
                            val calendarDay = CalendarDay(calendar)
                            calendarDay.labelColor = R.color.yellow
                            calendarDay.imageResource = R.drawable.baseline_food_bank_24

                            calendars.add(calendarDay)
                        }
                    }

                    // Update the CalendarView with the fetched days
                    calendarView.setCalendarDays(calendars)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to load calendar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // Call this function after setting up the calendar view
        loadEvents()

        calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener {
            override fun onClick(calendarDay: CalendarDay) {
                val day = String.format("%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format("%02d", calendarDay.calendar.get(Calendar.MONTH) + 1)
                val year = String.format("%04d", calendarDay.calendar.get(Calendar.YEAR))

                val dateKey = "$day-$month-$year" // Adjusted to match Firestore format

                if (events.containsKey(dateKey)) {
                    val db = FirebaseFirestore.getInstance()
                    db.collection("calendar")
                        .document(dateKey)
                        .get()
                        .addOnSuccessListener { document ->
                            if (document != null && document.exists()) {
                                val recipeMap = mapOf(
                                    "recipeName" to document.getString("name"),
                                    "recipeImage" to document.getString("imageLink"),
                                    "recipeInstruction" to document.getString("instructions"),
                                    "ingredient1" to document.getString("ingredient1"),
                                    "ingredient2" to document.getString("ingredient2"),
                                    "ingredient3" to document.getString("ingredient3"),
                                    "ingredient4" to document.getString("ingredient4"),
                                    "ingredient5" to document.getString("ingredient5"),
                                    "measure1" to document.getString("measure1"),
                                    "measure2" to document.getString("measure2"),
                                    "measure3" to document.getString("measure3"),
                                    "measure4" to document.getString("measure4"),
                                    "measure5" to document.getString("measure5")
                                )
                                // Call the onItemClick function with the fetched recipe
                                onItemClick(recipeMap)
                            } else {
                                Toast.makeText(baseContext, "No recipe found for this date", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(baseContext, "Failed to fetch recipe: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(baseContext, "Nothing to do on this date", Toast.LENGTH_SHORT).show()
                }
            }
        })

        calendarView.setOnPreviousPageChangeListener(object: OnCalendarPageChangeListener {
            override fun onChange() {
                val month = String.format("%02d", calendarView.currentPageDate.get(Calendar.MONTH) + 1)
                val year = calendarView.currentPageDate.get(Calendar.YEAR)
                Toast.makeText(baseContext, "$month/$year", Toast.LENGTH_SHORT).show()
            }
        })
        calendarView.setOnForwardPageChangeListener(object: OnCalendarPageChangeListener {
            override fun onChange() {
                val month = String.format("%02d", calendarView.currentPageDate.get(Calendar.MONTH) + 1)
                val year = calendarView.currentPageDate.get(Calendar.YEAR)
                Toast.makeText(baseContext, "$month/$year", Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun onItemClick(item: Map<String, Any?>) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("EXTRA_NAME", item["recipeName"] as? String)
        intent.putExtra("EXTRA_IMAGE", item["recipeImage"] as? String)
        intent.putExtra("EXTRA_INSTRUCTIONS", item["recipeInstruction"] as? String)
        intent.putExtra("EXTRA_INGREDIENT_1", item["ingredient1"] as? String)
        intent.putExtra("EXTRA_INGREDIENT_2", item["ingredient2"] as? String)
        intent.putExtra("EXTRA_INGREDIENT_3", item["ingredient3"] as? String)
        intent.putExtra("EXTRA_INGREDIENT_4", item["ingredient4"] as? String)
        intent.putExtra("EXTRA_INGREDIENT_5", item["ingredient5"] as? String)
        intent.putExtra("EXTRA_MEASURE_1", item["measure1"] as? String)
        intent.putExtra("EXTRA_MEASURE_2", item["measure2"] as? String)
        intent.putExtra("EXTRA_MEASURE_3", item["measure3"] as? String)
        intent.putExtra("EXTRA_MEASURE_4", item["measure4"] as? String)
        intent.putExtra("EXTRA_MEASURE_5", item["measure5"] as? String)
        startActivity(intent)
    }

    private fun replaceFragment(foodListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.recipe_frame_layout, foodListFragment)
        fragmentTransaction.commit()
    }

    // Logout function to be called from HomeFragment
    fun logout() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun loadDatabase() {
        lifecycleScope.launch {
            (application as RecipeApplication).db.recipeDao().getAll().collect { databaseList ->
                val recipeList = databaseList.map { entity ->
                    RecipeData(
                        entity.id,
                        entity.name,
                        entity.imageLink,
                        entity.instructions,
                        entity.ingredient1,
                        entity.ingredient2,
                        entity.ingredient3,
                        entity.ingredient4,
                        entity.ingredient5,
                        entity.measure1,
                        entity.measure2,
                        entity.measure3,
                        entity.measure4,
                        entity.measure5,
                    )
                }
                favoriteFragment.updateData(recipeList)
            }
        }
    }
}
