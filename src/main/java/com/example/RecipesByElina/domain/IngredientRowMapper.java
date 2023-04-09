package com.example.RecipesByElina.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class IngredientRowMapper implements RowMapper<Ingredient> {
	
	@Override
	public Ingredient mapRow(ResultSet rs, int rownum) throws SQLException {
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(rs.getInt("ingredient_id"));
		ingredient.setIngredient(rs.getString("ingredient"));
		ingredient.setRecipeId(rs.getInt("recipe_id"));
		ingredient.setQuantity(rs.getString("quantity"));
		ingredient.setUnit(rs.getString("unit"));
		return ingredient;
	}
}
