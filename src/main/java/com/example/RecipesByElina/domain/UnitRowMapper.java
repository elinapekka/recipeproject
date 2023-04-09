package com.example.RecipesByElina.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UnitRowMapper implements RowMapper<Unit> {
	
	@Override
	public Unit mapRow(ResultSet rs, int rownum) throws SQLException {
		Unit unit = new Unit();
		unit.setUnit(rs.getString("unit"));
		unit.setUnitId(rs.getInt("unit_id"));
		return unit;
	}
}
