package com.example.RecipesByElina.domain;

public class Instructions {
	
	private int instructionId;
	private int recipeId;
	private int stepNum;
	private String description;
	
	public Instructions() {
		
	}
	
	public Instructions(int instructionId, int recipeId, int stepNum, String description) {
		this.instructionId = instructionId;
		this.recipeId = recipeId;
		this.stepNum = stepNum;
		this.description = description;
	}
	
	public int getInstructionId() {
		return instructionId;
	}
	public void setInstructionId(int instructionId) {
		this.instructionId = instructionId;
	}
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	public int getStepNum() {
		return stepNum;
	}
	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
