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
import com.example.mealplanner.interfaces.OnListFragmentInteractionListener
import com.example.mealplanner.R
import com.example.mealplanner.dataClasses.Recipes

class RecipesRecyclerViewAdapter (
    private val recipes: List<Recipes>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<RecipesRecyclerViewAdapter.RecipeViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recipes, parent, false)
        return RecipeViewHolder(view)
    }

    inner class RecipeViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Recipes? = null
        val mRecipeName: TextView = mView.findViewById<View>(R.id.recipe_title) as TextView
        val mRecipeImage: ImageView = mView.findViewById<View>(R.id.recipe_image) as ImageView
        override fun toString(): String {
            return mRecipeName.toString()
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.mItem = recipe
        holder.mRecipeName.text = recipe.recipeName
        val imageUrl = recipe.recipeImage

        val radius = 50;
        val requestOptions = RequestOptions()
            .transform(CenterCrop(), RoundedCorners(radius))
        Glide.with(holder.mView)
            .load(imageUrl)
            .apply(requestOptions)
            .into(holder.mRecipeImage)
        holder.mView.setOnClickListener {
            holder.mItem?.let {recipe ->
                mListener?.onItemClick(recipe)
            }
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}