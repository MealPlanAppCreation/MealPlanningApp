package com.example.mealplanner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mealplanner.R
import com.example.mealplanner.dataClasses.RecipeData
import com.example.mealplanner.dataClasses.Recipes
import com.example.mealplanner.interfaces.OnListFragmentInteractionListener

class FavoriteAdapter(
    private var recipes: List<RecipeData>,
    private val onItemClickListener: (RecipeData) -> Unit
)
    : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>()
{
    fun updateFoods(newFoods: List<RecipeData>) {
        recipes = newFoods
        notifyDataSetChanged()
    }
    // ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeNameView: TextView = itemView.findViewById(R.id.recipeNameTv)
        val recipeImageView: ImageView = itemView.findViewById(R.id.recipeImageTv)

        // Bind the data and click listener
        fun bind(recipe: RecipeData, onItemClickListener: (RecipeData) -> Unit) {
            recipeNameView.text = recipe.name

            // Glide for image loading
            val radius = 50
            val requestOptions = RequestOptions()
                .transform(CenterCrop(), RoundedCorners(radius))
            Glide.with(recipeImageView.context)
                .load(recipe.imageLink)
                .apply(requestOptions)
                .into(recipeImageView)

            // Handle clicks
            itemView.setOnClickListener {
                onItemClickListener(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe, onItemClickListener) // Pass the click listener
    }
}