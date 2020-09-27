package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AgeCalculatorMySql {
	private static final String AGE_QUERY="SELECT DATEDIFF(NOW(),DOB)/365.25 FROM PERSON_DATE_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter PID:");
				pid=sc.nextInt();
			}
		
			
			//load jdbc drivers
			/*
			 * Class.forName("com.mysql.cj.jdbc.driver");
			*/
			
			//Establish Connection
			con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
			
			//create statement objects
			if(con!=null) {
				ps=con.prepareStatement(AGE_QUERY);
			}
			
			//set values to the prepared statment query
			if(ps!=null)
				ps.setInt(1, pid);
			//execute query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the query
			if(rs!=null) {
				if(rs.next()) {
					System.out.println("Person Age::"+rs.getFloat(1));
				}
				else
					System.out.println("Reord Not Found");
					
			}
			
		}//try
		 catch(SQLException se){
		    	se.printStackTrace();
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		    finally {
		    	//close all jdbc ojects
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
