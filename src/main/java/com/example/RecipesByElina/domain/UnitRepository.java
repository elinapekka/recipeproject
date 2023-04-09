package com.example.RecipesByElina.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UnitRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// retrieve all units
	@Transactional(readOnly=true)
	public List<Unit> findAll() {
		return jdbcTemplate.query("SELECT * FROM Units", new UnitRowMapper());
	}
	
	//
	@Transactional(readOnly = true)
	public Unit findById(int id) {
		List<Unit> unitList = jdbcTemplate.query("SELECT * FROM Units WHERE unit_id = " + id,
				new UnitRowMapper());
		
		Unit unit = unitList.get(0);
		
		return unit;
	}
	
	// add unit 
	public void addUnit(Unit unit) {
		jdbcTemplate.update(
				"INSERT INTO Units (unit) VALUES (?)", unit.getUnit());
	}
	
	// update unit
	public void updateUnit(Unit unit) {
		jdbcTemplate.update(
				"UPDATE Units SET unit = (?) WHERE unit_id = (?)", unit.getUnit(), unit.getUnitId());
	}
	
	// delete unit 
	public void deleteUnit(int id) {
		jdbcTemplate.update("DELETE FROM Units WHERE unit_id = (?)", id);
	}
	
}
