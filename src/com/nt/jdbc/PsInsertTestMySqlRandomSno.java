package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class PsInsertTestMySqlRandomSno {
	private static final String INSERT_STUD_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null;
		float avg=0.0f;	
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student Name:");
				name=sc.next(); //gives ramesh
				
				System.out.println("Enter Studnet address:");
				addrs=sc.next();//gives delhi
				
				System.out.println("Enter average:");
				avg=sc.nextFloat();//fives 56.77
				
			}//if
			
		    
		    //register jdbc driver
		  // Class.forName(oracle.jdbc.driver.OracleDriver);
		   
		    
		   //establish connection
		   con=DriverManager.getConnection("jdbc:mysql:///advjavasql","root","root");
		  //create statement
		    if(con!=null) 
		    	ps=con.prepareStatement(INSERT_STUD_QUERY);
		 
		    
		  //send and execute SQL query 
		    if(ps!=null ) {
		    	ps.setInt(1,new Random().nextInt(10000));
		    	ps.setString(2,name);
		    	ps.setString(3,addrs);
		    	ps.setFloat(4,avg);
		        count=ps.executeUpdate();
		    }
		  //process the result
		    if(count==0)
		    	System.out.println("Records not inserted");
		    else
		    	System.out.println(+count+" no. of Records  inserted");
		   
		   		
		}//try
		catch(SQLException se) {
			System.out.println(se.getErrorCode());
			if(se.getErrorCode()==12899)
				 System.out.println("You can't enter values more than column size");
			 else if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				 System.out.println("SQL query Syntax Error");
			 else
				 System.out.println("Unknown JDBC problem");
			 se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//close jdbc objects
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
