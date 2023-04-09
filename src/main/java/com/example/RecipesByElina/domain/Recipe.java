package com.example.RecipesByElina.domain;

public class Recipe {
	private int id;
	private String name;
	private int time;
	private String shortDescription;
	// private String pic;
	
	public Recipe() {
		
	}
	
	public Recipe(int id, String name, int time, String shortDescription) {
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	
}
