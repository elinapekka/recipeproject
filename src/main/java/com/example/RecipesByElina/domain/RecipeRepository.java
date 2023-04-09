package com.example.RecipesByElina.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RecipeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// retrieve the list of all recipes in the Recipes table
	@Transactional(readOnly = true)
	public List<Recipe> findAll() {
		return jdbcTemplate.query("SELECT * FROM Recipes ORDER BY name", new RecipeRowMapper());
	}

	// retrieve a recipe using the id of the recipe
	@Transactional(readOnly = true)
	public Recipe findById(int id) {
		List<Recipe> queryResult = jdbcTemplate.query("SELECT * FROM Recipes WHERE recipe_id =" + id,
				new RecipeRowMapper());
		Recipe returnRecipe = new Recipe(queryResult.get(0).getId(), queryResult.get(0).getName(),
				queryResult.get(0).getTime(), queryResult.get(0).getShortDescription());
		return returnRecipe;
	}

	// add a recipe to the Recipes table
	// returns the id of the newly created recipe so it can then be redirected into
	// the editing page of the recipe.
	public int addRecipe(Recipe recipe) {

		jdbcTemplate.update("INSERT INTO Recipes(name, time_amount, short_description) VALUES (?, ?, ?)",
				recipe.getName(), recipe.getTime(), recipe.getShortDescription());

		List<Recipe> newlyCreatedRecipe = jdbcTemplate
				.query("SELECT * FROM Recipes WHERE name = '" + recipe.getName() + "'", new RecipeRowMapper());

		int newRecipeId = newlyCreatedRecipe.get(0).getId();

		return newRecipeId;
	}

	// update all the fields of a recipe (excluding the id)
	public void updateRecipe(Recipe recipe) {
		jdbcTemplate.update(
				"UPDATE Recipes SET name = (?), time_amount = (?), short_description = (?) WHERE recipe_id = (?)",
				recipe.getName(), recipe.getTime(), recipe.getShortDescription(), recipe.getId());
	}

	// deletes recipe from the Recipes table, as well as all the relevant
	// instructions and ingredients using the id of the recipe.
	public void deleteRecipeById(int recipeId) {
		jdbcTemplate.update("DELETE FROM Instructions WHERE recipe_id = (?)", recipeId);
		jdbcTemplate.update("DELETE FROM Ingredients WHERE recipe_id = (?)", recipeId);
		jdbcTemplate.update("DELETE FROM Recipes WHERE recipe_id = (?)", recipeId);

	}

}
