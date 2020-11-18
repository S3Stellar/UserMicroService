package com.example.demo.data;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RoleConverter implements AttributeConverter<String[], String> {

	private ObjectMapper jackson;

	public RoleConverter() {
		this.jackson = new ObjectMapper();
	}

	@Override
	public String convertToDatabaseColumn(String[] attribute) {
		String strRoles = "";
		try {
			strRoles = jackson.writeValueAsString(attribute);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return strRoles;
	}

	@Override
	public String[] convertToEntityAttribute(String dbData) {
		String[] rolesArray = null;
		try {
			rolesArray = jackson.readValue(dbData, String[].class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return rolesArray;
	}

}
