package com.datainfo.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.ResultSetMetaData;

public class QueryExecutor {

	public String command;
	private Connection connection;
	private int rowNum = 1;

	Map<Integer, Map<String, Object>> mapValues = new HashMap<Integer, Map<String, Object>>();

	public QueryExecutor(String select, Connection connection) throws SQLException {
		this.command = select;
		this.connection = connection;

		if (command.startsWith("SELECT"))
			executeQuery(command, connection);
		else
			executeCommand();
	}

	public boolean executeCommand() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(command);
		boolean rs = ps.execute();

		return rs;
	}

	private void executeQuery(String select, Connection connection) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(select);
		ResultSet rs = ps.executeQuery();

		ResultSetMetaData metaRS = (ResultSetMetaData) rs.getMetaData();
		int columnCount = metaRS.getColumnCount();

		Integer index = 1;
		Map<String, Object> map;
		while (rs.next()) {
			map = new HashMap<String, Object>();

			for (int i = 1; i <= columnCount; i++) {
				map.put(metaRS.getColumnName(i).toUpperCase(), rs.getObject(i));
			}

			mapValues.put(index, map);
			index++;
		}
	}

	public void refresh() throws SQLException {
		executeQuery(command, connection);
	}

	public Object fieldget(String fieldName) throws SQLException {
		return mapValues.get(rowNum).get(fieldName.toUpperCase());
	}

	public void skip(int i) {
		rowNum++;
	}

	public void goTop() {
		rowNum = 1;
	}

	public boolean eof() throws SQLException {
		return rowNum <= mapValues.size() ? true : false;
	}
}
