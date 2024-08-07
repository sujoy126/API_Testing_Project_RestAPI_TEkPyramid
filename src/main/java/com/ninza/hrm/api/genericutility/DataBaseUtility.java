package com.ninza.hrm.api.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public  class DataBaseUtility {
	PropertyFileUtility pfLib = new PropertyFileUtility();
	
	Connection conn;
	
	public void getConnection(String DBUrl, String DB_UserName, String DB_Password) throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(DBUrl, DB_UserName, DB_Password);
		} catch (Exception e) {
		}
	}

	public void getConnection() throws Throwable {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(pfLib.getDataFromPropertiesFile("DBUrl"),
					pfLib.getDataFromPropertiesFile("DB_UserName"),
					pfLib.getDataFromPropertiesFile("DB_Password"));
		} catch (Exception e) {
		}
	}

	public ResultSet executeSelectQuary(String quary) throws Throwable {
		ResultSet result = null;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeQuery(quary);
		} catch (Exception e) {
		}
		return result;
	}

	public int executeNonSelectQuary(String quary) throws Throwable {
		int result = 0;
		try {
			Statement con = conn.createStatement();
			result = con.executeUpdate(quary);
		} catch (Exception e) {
		}
		return result;
	}

	
	
	public  boolean  executeQueryVerifyAndGetData(String query, int columnIndex, String expectedData) throws SQLException {
		boolean flag = false;
		 ResultSet reslt =  conn.createStatement().executeQuery(query);
		while(reslt.next()) {
			if(reslt.getString(columnIndex).equals(expectedData)) {
				flag = true;
				break;
			}			
		}
		if(flag) {
			System.out.println(expectedData+" data verified in database table");
			return true;
		}
		else {
			System.out.println(expectedData+" data  not verified in data base table");
			return false;
		}	
	}
	
	public void closeDataBaseConnection() throws SQLException {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}
}
