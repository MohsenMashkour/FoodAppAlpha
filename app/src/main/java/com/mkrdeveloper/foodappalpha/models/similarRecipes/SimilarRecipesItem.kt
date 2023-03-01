package com.mkrdeveloper.foodappalpha.models.similarRecipes

data class SimilarRecipesItem(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)