package com.example.RecipesByElina.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InstructionRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// retrieve all the instructions of the Instructions table
	@Transactional(readOnly = true)
	public List<Instructions> findAll() {
		return jdbcTemplate.query("SELECT * FROM Instructions", new InstructionRowMapper());
	}
	
	
	//find an instruction (singular) using the instruction id
	@Transactional(readOnly = true)
	public Instructions findById(int id) {
		List<Instructions> instructionList = jdbcTemplate.query("SELECT * FROM Instructions WHERE instruction_id = " + id,
				new InstructionRowMapper());
		
		Instructions instruction = instructionList.get(0);
		
		return instruction;
	}
	
	// find all the instructions of a recipe 
	@Transactional(readOnly = true)
	public List<Instructions> findByRecipeId(int id) {
		return jdbcTemplate.query("SELECT * FROM Instructions WHERE recipe_id = " + id + " ORDER BY step_num",
				new InstructionRowMapper());
	}
	
	
	// add an instruction to the Instructions table
	public void addInstruction(Instructions instruction) {
		jdbcTemplate.update(
				"INSERT INTO Instructions(recipe_id, step_num, description) VALUES (?, ?, ?)",
				instruction.getRecipeId(), instruction.getStepNum(), instruction.getDescription());
	}
	
	//update the description of an instruction
	public void updateInstruction(Instructions instruction) {
		jdbcTemplate.update(
				"UPDATE Instructions SET description = (?) WHERE instruction_id = (?)",
				instruction.getDescription(), instruction.getInstructionId());
	}
	
	// delete an instruction from the Instructions table
	// at the same time updates the step numbers of other instruction steps of the recipe so it goes in order 
	public void deleteInstruction(Instructions instruction) {
		jdbcTemplate.update("UPDATE Instructions SET step_num = (step_num - 1) WHERE step_num > (?) AND recipe_id = (?)",
				instruction.getStepNum(), instruction.getRecipeId());
		jdbcTemplate.update("DELETE FROM Instructions WHERE instruction_id = (?)", instruction.getInstructionId());
	}
	
	
	// allows you to get the new step number for a recipe when adding a new instruction
	@Transactional(readOnly = true)
	public int getNewStepNum(int recipeId) {

		String sql = "SELECT MAX(step_num) FROM Instructions WHERE recipe_id = " + recipeId;

		if(jdbcTemplate.queryForObject(sql, Integer.class) == null) {
			return 1;
		} else {
			return jdbcTemplate.queryForObject(sql, Integer.class) + 1;
		}
	}

}
