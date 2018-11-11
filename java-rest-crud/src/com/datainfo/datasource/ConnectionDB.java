package com.datainfo.datasource;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class ConnectionDB {

	private Connection conn;

	public ConnectionDB() {

		try {
			String driverName = "org.gjt.mm.mysql.Driver";
			Class.forName(driverName);

			conn = (Connection) DriverManager.getConnection("jdbc:mySql://localHost:3306/datainfo", "root", "root");

		} catch (Exception e) {
			System.out.println("Erro de Conexão!");
		}

	}

	public Connection getConn() {
		return conn;
	}

}
