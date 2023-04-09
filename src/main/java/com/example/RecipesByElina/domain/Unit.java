package com.example.RecipesByElina.domain;

public class Unit {
	
	private int unitId;
	private String unit;
	
	public Unit() {
		
	}
	
	public Unit(int unitId, String unit) {
		this.unitId = unitId;
		this.unit = unit;
	}
	
	public int getUnitId() {
		return unitId;
	}

	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
