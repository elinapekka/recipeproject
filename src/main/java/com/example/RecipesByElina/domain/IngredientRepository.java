package com.example.RecipesByElina.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class IngredientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	// retrieve all the ingredients from the Ingredients table 
	@Transactional(readOnly = true)
	public List<Ingredient> findAll() {
		return jdbcTemplate.query("SELECT * FROM Ingredients", new IngredientRowMapper());
	}
	
	// find the ingredient using ingredient's id
	@Transactional(readOnly = true)
	public Ingredient findById(int id) {
		List<Ingredient> ingredientList = jdbcTemplate.query("SELECT * FROM Ingredients WHERE ingredient_id = " + id, new IngredientRowMapper());
		Ingredient ingredient = ingredientList.get(0);
		return ingredient;
	}
	
	// find the list of ingredients of a certain recipe
	@Transactional(readOnly = true)
	public List<Ingredient> findByRecipeId(int id) {
		return jdbcTemplate.query("SELECT * FROM Ingredients WHERE recipe_id = " + id, new IngredientRowMapper());
	}
	
	// get the recipe's id using the id of the ingredient
	@Transactional(readOnly = true)
	public int findRecipeIdByIngredientId(int id) {
		List<Ingredient> ingredientList = jdbcTemplate.query("SELECT * FROM Ingredients WHERE ingredient_id = " + id, new IngredientRowMapper());
		int recipeId = ingredientList.get(0).getRecipeId();
		return recipeId;
	}
	
	
	// add an ingredient to the Ingredients table
	public void addIngredient(Ingredient ingredient) {
		jdbcTemplate.update("INSERT INTO Ingredients (recipe_id, ingredient, quantity, unit) VALUES (?, ?, ?, ?)",
				ingredient.getRecipeId(), ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getUnit());
	}
	
	
	// delete ingredient using ingredient id
	public void deleteIngredientById(int ingredientId) {
		jdbcTemplate.update("DELETE FROM Ingredients WHERE ingredient_id = (?)", ingredientId);
	}
	
	public void updateIngredient(Ingredient ingredient) {
		jdbcTemplate.update("UPDATE Ingredients SET ingredient = (?), quantity = (?), unit = (?) WHERE ingredient_id = (?)",
				ingredient.getIngredient(), ingredient.getQuantity(), ingredient.getUnit(), ingredient.getIngredientId());
	}
	
}
