package com.example.RecipesByElina.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class InstructionRowMapper implements RowMapper<Instructions> {
	@Override
	public Instructions mapRow(ResultSet rs, int rownum) throws SQLException {
		Instructions instruction = new Instructions();
		instruction.setInstructionId(rs.getInt("instruction_id"));
		instruction.setStepNum(rs.getInt("step_num"));
		instruction.setRecipeId(rs.getInt("recipe_id"));
		instruction.setDescription(rs.getString("description"));
		return instruction;
	}

}
