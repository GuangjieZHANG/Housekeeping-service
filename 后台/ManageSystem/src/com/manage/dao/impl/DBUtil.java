package com.manage.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	/**
	 * public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	 * public static final String URL = "jdbc:oracle:thin:@//172.16.81.118:1521/orcl"; 
	 * public static final String USER = "dzzwpt"; 
	 * public static final String PWD = "dzzwpt";
	 */

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/managesystem";
	public static final String USER = "root";
	public static final String PWD = "admin";
	

	private DBUtil() {
	}

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() throws SQLException {
		return DriverManager.getConnection(URL, USER, PWD);
	}

	public static void closeAll(ResultSet rs, Statement sta, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (sta != null)
				sta.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			getConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
