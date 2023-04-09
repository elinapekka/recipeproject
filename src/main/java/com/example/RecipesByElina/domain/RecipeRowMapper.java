package com.example.RecipesByElina.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RecipeRowMapper implements RowMapper<Recipe> {
	
	@Override
	public Recipe mapRow(ResultSet rs, int rownum) throws SQLException {
		Recipe recipe = new Recipe();
		recipe.setId(rs.getInt("recipe_id"));
		recipe.setName(rs.getString("name"));
		recipe.setTime(rs.getInt("time_amount"));
		recipe.setShortDescription(rs.getString("short_description"));
		return recipe;
	}
}
