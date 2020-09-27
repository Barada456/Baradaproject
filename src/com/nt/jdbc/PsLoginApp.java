package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp {
	private static final String AUTH_QUERY="SELECT COUNT(*) FROM USERINFO WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		String uname=null;
		String pwd=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
			    sc=new Scanner(System.in);
			    if(sc!=null) {
				System.out.println("Enter Username::");
				uname=sc.nextLine(); //gives raja				
				System.out.println("Enter Pwd::");
				pwd=sc.nextLine(); //gives rani
			}//if
				
				//register jdbc drivers 
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
				
				//create preparedStatement object having precomiled sql query
				if(con!=null)
					ps=con.prepareStatement(AUTH_QUERY);
				
				//set values to precompiled query
				if(ps!=null) {
				   ps.setString(1,uname);
				   ps.setString(2,pwd);
				}
				//execute precompiled query
				if(ps!=null) 
					rs=ps.executeQuery();
				//process the ressultset
				if(rs!=null) {
					rs.next();
					count=rs.getInt(1);
				}
				//process the result
				if(count==0)
					System.out.println("INVALID LOGIN DETAILS..");
				else
					System.out.println("YOU ARE SUCCESSFULLY LOGED IN");
			}//try
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
