package com.example.mealplanner

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import com.example.mealplanner.dataClasses.RecipeData
import com.example.mealplanner.database.RecipeApplication
import com.example.mealplanner.databinding.ActivityMainBinding
import com.example.mealplanner.fragments.FavoriteListFragment
import com.example.mealplanner.fragments.RecipesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
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
        /*
        calendarView = findViewById(R.id.calendar)
        val calendars: ArrayList<CalendarDay> = ArrayList()
        val calendar = Calendar.getInstance()
        calendar.set(2024, 11, 20)
        val calendarDay = CalendarDay(calendar)
        calendarDay.labelColor = R.color.yellow
        calendarDay.imageResource = R.drawable.baseline_food_bank_24
        calendars.add(calendarDay)
        events["20-12-2024"] = "Recipe"
        calendarView.setCalendarDays(calendars)
        calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener {
            override fun onClick(calendarDay: CalendarDay) {
                // Adjust the month to match the format in the events map
                val day = String.format("%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format("%02d", calendarDay.calendar.get(Calendar.MONTH) + 1) 
                val year = String.format("%04d", calendarDay.calendar.get(Calendar.YEAR))

                // Format the key to match the events map
                val dateKey = "$day-$month-$year"

                if (events.containsKey(dateKey)) {
                    Toast.makeText(baseContext, events[dateKey], Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "nothing to do", Toast.LENGTH_SHORT).show()
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

         */
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
