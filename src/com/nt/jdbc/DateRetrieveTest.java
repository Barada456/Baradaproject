package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetrieveTest {
	private static final String DATE_RETRIVE_QUERY="SELECT PID,PNAME,PADDRS,DOB,DOM,DOJ FROM PERSON_DATE_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int id=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int pid=0;
		String pname=null,paddrs=null;
		java.sql.Date sqdob=null,sqdom=null,sqdoj=null;
		SimpleDateFormat sdf=null;
		String sdob=null,sdom=null,sdoj=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
			System.out.println("Enter PID:");
			id=sc.nextInt();
			}
			//register jdbc drivers
			//load jdbc drivers
			/*
			 * Class.forName("com.mysql.cj.jdbc.driver");
			*/
			
			//Establish Connection
			con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
			
			//create preparedstatement 
			if(con!=null){
			ps=con.prepareStatement(DATE_RETRIVE_QUERY);
			}
			
			//set values to preparedstatement query params
			if(ps!=null)
				ps.setInt(1,id);
			
			//execute query
			if(ps!=null)
				rs=ps.executeQuery();
			
			//process the resultset object
			if(rs!=null) {
				while(rs.next()) {
					pid=rs.getInt(1);
					pname=rs.getString(2);
					paddrs=rs.getString(3);
					
					sqdob=rs.getDate(4);
					sqdom=rs.getDate(5);
					sqdoj=rs.getDate(6);
					
					//convert java.sql.date class object to string date format
					sdf=new SimpleDateFormat("MMM-dd-YY");
					sdob=sdf.format(sqdob);
					sdom=sdf.format(sqdom);
					sdoj=sdf.format(sqdoj);
					
					System.out.println(+pid+" "+pname+" "+paddrs+" "+sqdob+" "+sqdom+" "+sqdoj);
					System.out.println(+pid+" "+pname+" "+paddrs+" "+sdob+" "+sdom+" "+sdoj);
					
				}//while
			}//if
			else
				System.out.println("Record Not found!");
			
			
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
			if(con!=null) 
				con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
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
