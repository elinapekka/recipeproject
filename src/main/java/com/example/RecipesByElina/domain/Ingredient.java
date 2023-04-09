package com.example.RecipesByElina.domain;

public class Ingredient {
	
	private int ingredientId;
	private int recipeId;
	private String ingredient;
	private String quantity;
	private String unit;
	
	public Ingredient() {
		
	}
	
	public Ingredient(int ingredientId, int recipeId, String ingredient, String quantity, String unit) {
		this.ingredientId = ingredientId;
		this.recipeId = recipeId;
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.unit = unit;
	}

	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	public int getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
