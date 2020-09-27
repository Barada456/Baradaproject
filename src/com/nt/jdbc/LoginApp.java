package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		//read inputs
		Scanner sc=null;
		String user=null,pwd=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Username:");
				user=sc.nextLine(); //gives Jogesh
				System.out.println("Enter Password:");
				pwd=sc.nextLine(); //jogesh@456
			}
			//convert input values as required for the SQL query
			user="'"+user+"'"; //gives 'Jogesh'
			pwd="'"+pwd+"'"; //gives 'jogesh@456'
			
			//register jdbc drivers
			//Class.forName(oracle.jdbc.driver.OracleDriver);
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","human");
			
			//create statement object
			if(con!=null)
				st=con.createStatement();
			
			//prepare sql query
			//select count(*) from userInfo where uname='Jogesh' and pwd='jogesh@456';
			query="SELECT COUNT(*) FROM USERINFO WHERE UNAME="+user +" AND PWD="+pwd;
			
			System.out.println(query);
			
			//send and execute query
			if(st!=null)
			  rs=st.executeQuery(query);
			
			//process the resultset
			if(rs!=null) {
				rs.next();
				count=rs.getInt(1);
			}
			
			if(count==0)
				System.out.println("Invalid Credentials");
			else
				System.out.println("Valid Credentials");
			
			}
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
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st!=null) 
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null) 
					con.close();
			}
			catch(SQLException se){
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
		}
	}

}
