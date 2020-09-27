package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySqlDataTransfer {
	private static final String SYND_ALL_DATA="SELECT BANKACCNO,HOLDERNAME,BALANCE FROM JDBC_SYND_BANK ";
	private static final String INDIAN_ALL_DATA="INSERT INTO JDBC_INDIAN_BANK(BANKACCNO,HOLDERNAME,BALANCE) VALUES(?,?,?) ";
	public static void main(String[] args) {
		Connection oraCon=null,sqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Long accno=0l;
		String name=null;
		float balance=0.0f;
		try {
			//load jdbc drivers
			/*
			 * Class.forName("oracle.jdbc.driver.OracleDriver");
			 * Class.forName("com.mysql.cj.jdbc.driver");
			 */
			//Establish connections
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			sqlCon=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
			
			//create statement objects
			if(oraCon!=null)
				st=oraCon.createStatement();
			if(sqlCon!=null)
				ps=sqlCon.prepareStatement(INDIAN_ALL_DATA);
			//execute select query in oracle and get ResultSet object 
			if(st!=null) 
				rs=st.executeQuery(SYND_ALL_DATA);
			if(ps!=null && rs!=null) {
				while(rs.next()) {
					//get each record from oracle db table through result set
					accno=rs.getLong(1);
					name=rs.getString(2);
					balance=rs.getFloat(3);
					//set values to preapred statement object query params(inserting to mysql db table)
					ps.setLong(1,accno);
					ps.setNString(2,name);
					ps.setFloat(3,balance);
					ps.executeUpdate();
				}//while
				System.out.println("Records are copied from SYNDICATE Database to Indian Bank DataBase...");
			}//if
				
		}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if(rs!=null) 
						rs.close();
					}
					catch(SQLException se) {
						se.printStackTrace();
					}
			  
				try {
				if(ps!=null) 
					ps.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
				try {
					if(st!=null) 
						st.close();
					}
					catch(SQLException se) {
						se.printStackTrace();
					}
				try {
					if(oraCon!=null) 
						oraCon.close();
					}
					catch(SQLException se) {
						se.printStackTrace();
					}
				
				try {
					if(sqlCon!=null) 
						sqlCon.close();
					}
					catch(SQLException se) {
						se.printStackTrace();
					}
			}//finally

	}//main

}//class
